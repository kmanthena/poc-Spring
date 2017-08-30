package com.nisum.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.nisum.domain.User;

public interface UserRepository extends MongoRepository<User, String>  {
    
	public void deleteByStrUserId(String strUserId);

	
}