package com.nisum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.domain.OrderHeader;
import com.nisum.domain.OrderLine;
import com.nisum.repository.OrderHeaderInfo;
import com.nisum.repository.OrderLineInfo;

@Service
public class OrderService {
    
	@Autowired
	OrderHeaderInfo orderheaderInfo;
	
	@Autowired
	OrderLineInfo orderLineInfo;
	
	public boolean validateUniqueOrderNo(String strOrderNo) {
		OrderHeader order = orderheaderInfo.findOne(strOrderNo);
		if(order == null)
			return true;
		
		return false;
    }
	
	public void createNewOrder(OrderHeader orderHeader) {
		orderheaderInfo.save(orderHeader);
	}
	
	public void createNewOrderLines(OrderLine orderLine) {
		orderLineInfo.save(orderLine);
	}
}