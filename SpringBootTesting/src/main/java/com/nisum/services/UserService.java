package com.nisum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.domain.User;
import com.nisum.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	public List<User> fetchAllUsers() {
		List<User> users = userRepository.findAll();
		return users;
	}
	
	
	public User findUser(String strUserId) {
		User user = userRepository.findOne(strUserId);
		return user;
	}
	
	public void createNewUser(User user) {
		userRepository.save(user);
	}
	
	public void deleteNewUser(User user) {
		userRepository.delete(user);
	}
}