package com.nisum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.domain.OrderLine;

public interface OrderLineInfo extends MongoRepository<OrderLine, String>  {
    
	public OrderLine findByOrderStrOrderNo(String strOrderNo);
	
}