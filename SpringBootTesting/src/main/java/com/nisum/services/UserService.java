package com.nisum.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import com.nisum.domain.User;
import com.nisum.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
    LoginService loginService;
	
	@Autowired
    UserService userService;
	
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
	
	public void deleteNewUser(String strUserId) {
		userRepository.deleteByStrUserId(strUserId);
	}
	
	public String createUser(String userId, String password, String firstName, String lastName) {

		User user = loginService.validateUserInDB(userId, password);
			if(user!= null){
				return "User ID already exists";
			}
			//Create new user if userId is not available.
	        if (userId == null || (userId.trim().length() <= 0)) {
	            return "User ID is empty. Enter Valid Values.";
	        }
	        //Create New user if password is not available.
	        else if (password == null || (password.trim().length() <= 0)) {
	            return "Password is empty. Enter valid Values";
	        }
	        else {
	        	User newUser = new User();
	        	newUser.setStrUserId(userId);
	        	newUser.setStrPassword(password);
	        	newUser.setStrFirstName(firstName);
				newUser.setStrLastName(lastName);
				userService.createNewUser(newUser);
				
				return null;
	        }
	}
}