package com.nisum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.domain.OrderHeader;

public interface OrderHeaderInfo extends MongoRepository<OrderHeader, String>  {
    
	
}