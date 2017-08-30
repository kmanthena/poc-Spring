package com.nisum.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nisum.domain.Order;
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
		model.addAttribute("existingOrders", orderService.getExistingOrders());
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
        String strResponse = orderService.createNewOrder(order);
        if( strResponse != null){
			model.put("errorMessage", strResponse);
        }
        return "createNewOrder";
			
    }
	
}