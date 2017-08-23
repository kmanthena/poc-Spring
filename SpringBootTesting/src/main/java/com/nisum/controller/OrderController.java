package com.nisum.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nisum.domain.Order;
import com.nisum.domain.OrderHeader;
import com.nisum.domain.OrderLine;
import com.nisum.repository.OrderHeaderInfo;
import com.nisum.repository.OrderLineInfo;
import com.nisum.services.OrderService;

@Controller
@SessionAttributes("name")
public class OrderController {
  	
	@Autowired
	OrderService orderService;
	
	@Autowired
	OrderHeaderInfo orderHeaderInfo;
	
	@Autowired
	OrderLineInfo orderLineInfo;
	
	@RequestMapping(value="/existing-Order", method = RequestMethod.GET)
    public String showExistingOrder(ModelMap model){
		
		System.out.println(" existing Orders get method");
		
		List<OrderHeader> order = orderHeaderInfo.findAll();
		System.out.println(" No of orders in system : "+order.size());
		model.addAttribute("existingOrders", order);
        return "existing-Order";
	}
        
	@RequestMapping(value="/createNewOrder", method = RequestMethod.GET)
    public String showCreateOrder(ModelMap model){
		System.out.println(" Order get method");
        return "createNewOrder";
    }
    
//	@RequestMapping(value="/addOrderLines", method = RequestMethod.POST)
//    public void addOrderLines(ModelMap model, @RequestBody List<OrderLine> lines){
//		if(!lines.isEmpty()) {
//			lOrderLine = lines;
//			System.out.println(" Orderliens are added ");
//		}
//    }
	
	@RequestMapping(value="/createNewOrder", method = RequestMethod.POST)
	@ResponseBody
	 public String createNewOrder(ModelMap model, @RequestBody Order order){
		
        System.out.println(" Order available with multiple details"+order.getStrOrderNo());
        List<OrderLine> lOrderLine = order.getlOrderLine();
        System.out.println(" Orderlines available in Order :: "+lOrderLine.size());
        
        if(!lOrderLine.isEmpty()) {
	        System.out.println(" Orderlines available  :: "+lOrderLine.size());
	        List<String> lineNumbers = new ArrayList<String>();
	        
	        lOrderLine.forEach((temp) -> {
	        	if(lineNumbers.contains(temp.getStrOrderLineNo())) {
	        		model.put("errorMessage", " Not unique line no "+temp.getStrOrderLineNo());
	        	}
	        	else {
	        		lineNumbers.add(temp.getStrOrderLineNo());
	        	}
	        });
		}
		else {
			System.out.println("Order lines are not avaialble");
			model.put("errorMessage", "Order Lines not available");
		}
        
        OrderHeader orderHeader = new OrderHeader();
        orderHeader.setStrFirstName(order.getStrFirstName());
        orderHeader.setStrLastName(order.getStrLastName());
        orderHeader.setStrOrderNo(order.getStrOrderNo());
        orderHeader.setStrPaymentInfo(order.getStrPaymentInfo());
        orderHeader.setStrShipZipCode(order.getStrShipZipCode());
        
        if(orderService.validateUniqueOrderNo(orderHeader.getStrOrderNo()) && model.get("errorMessage") == null){
        	System.out.println(" Creating new Order");
        	orderService.createNewOrder(orderHeader);
        	lOrderLine.forEach(temp -> orderService.createNewOrderLines(temp));
        }
        else {
        	System.out.println("Order No is not unique");
			model.put("errorMessage", "Order No is not unique");
        }
        
        return "createNewOrder";
			
    }
	
}