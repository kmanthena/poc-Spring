package com.nisum.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.domain.Order;
import com.nisum.domain.OrderHeader;
import com.nisum.domain.OrderLine;
import com.nisum.repository.OrderHeaderInfo;
import com.nisum.repository.OrderLineInfo;

@Service
public class OrderService {

	@Autowired
	OrderHeaderInfo orderHeaderInfo;

	@Autowired
	OrderLineInfo orderLineInfo;

	public boolean validateUniqueOrderNo(String strOrderNo) {
		OrderHeader order = orderHeaderInfo.findOne(strOrderNo);
		if(order == null)
			return true;

		return false;
	}

	public String createNewOrder(Order order) {
		List<OrderLine> lOrderLine = order.getlOrderLine();
        
        String strErrorResponse = null;
        
        if(lOrderLine != null && !lOrderLine.isEmpty()) {
	        System.out.println(" Orderlines available  :: "+lOrderLine.size());
	        
	        if(!validateUniqueOrderLineNo(lOrderLine)) {
				strErrorResponse = "Order Lines is not unique";
	        }

		}
		else {
			System.out.println("Order lines are not avaialble");
			strErrorResponse = "Order Lines not available";
		}
        
        if(strErrorResponse == null){
        	OrderHeader orderHeader = new OrderHeader();
            orderHeader.setStrFirstName(order.getStrFirstName());
            orderHeader.setStrLastName(order.getStrLastName());
            orderHeader.setStrOrderNo(order.getStrOrderNo());
            orderHeader.setStrPaymentInfo(order.getStrPaymentInfo());
            orderHeader.setStrShipZipCode(order.getStrShipZipCode());
            
            if(validateUniqueOrderNo(orderHeader.getStrOrderNo())){
            	System.out.println(" Creating new Order");
            	createNewOrder(orderHeader);
            	lOrderLine.forEach(temp -> createNewOrderLines(temp));
            }
            else {
            	System.out.println("Order No is not unique");
            	strErrorResponse = "Order No is not unique";
            }
        }
        
        
        return strErrorResponse;
	}
	
	public void createNewOrder(OrderHeader orderHeader) {
		orderHeaderInfo.save(orderHeader);
	}

	public void createNewOrderLines(OrderLine orderLine) {
		orderLineInfo.save(orderLine);
	}
	
	
	public boolean validateUniqueOrderLineNo( List<OrderLine> lOrderLine) {
        List<String> lineNumbers = new ArrayList<String>();

		boolean isValidOrderLine = true;
		for(int i=0; i<lOrderLine.size(); i++) {
			
			OrderLine orderLine = lOrderLine.get(i);
			if(lineNumbers.contains(orderLine.getStrOrderLineNo())) {
				isValidOrderLine = false;
				System.out.println("false");
	    	}
	    	else {
	    		lineNumbers.add(orderLine.getStrOrderLineNo());
	    	}
		}
		return isValidOrderLine;
		
	}


	public List<OrderHeader> getExistingOrders() {
		
		return orderHeaderInfo.findAll();
	}
}