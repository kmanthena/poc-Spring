package com.nisum.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nisum.domain.User;

@Service
public class LoginService {
    
	@Autowired
	UserService userService;

	public User validateUserLogin(String userid, String password) {

		User user = userService.findUser(userid);		
		/*if("admin".equals(userid)){
			User newUser = new User();
			newUser.setStrFirstName("Kishore");
			newUser.setStrLastName("Karthik");
			return newUser;

		}*/
		
		if(user!= null) {
			System.out.println(" UserList from DB"+user.strUserId);
			String strPassword = user.getStrPassword();
			if(strPassword.equals(password))
				return user;
		}
		
		System.out.println(" No valid user. So return null");
		return null;
    }
	
	public User validateUserInDB(String userid, String password) {

		User user = userService.findUser(userid);
		
		if(user!= null) {
			System.out.println(" UserList from DB :: "+user.getStrUserId());
			System.out.println(" UserList from DB :: "+user.getStrPassword());
			return user;
		}
		else {
			return null;			
		}
    }
}