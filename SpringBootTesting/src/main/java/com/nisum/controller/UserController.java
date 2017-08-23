package com.nisum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nisum.domain.User;
import com.nisum.services.LoginService;
import com.nisum.services.UserService;

@Controller
@SessionAttributes("name")
public class UserController {
    
	@Autowired
    LoginService loginService;
	
	@Autowired
    UserService userService;
    
	@RequestMapping(value="/createNewUser", method = RequestMethod.GET)
    public String showCreateUserPage(ModelMap model){
        return "createNewUser";
    }
    
	@RequestMapping(value="/createNewUser", method = RequestMethod.POST)
    public String createNewUser(ModelMap model, @RequestParam String userId, 
    		@RequestParam String password, @RequestParam String firstName, @RequestParam String lastName){
        
        User user = loginService.validateUserInDB(userId, password);
		if(user!= null){
        	model.put("errorMessage", "User ID already exists");
			return "createNewUser";
		}
		//Create new user if userId is not available.
        if (userId == null || (userId.trim().length() <= 0)) {
        	model.put("errorMessage", "User ID is empty. Enter Valid Values.");
            return "createNewUser";
        }
        //Create New user if password is not available.
        else if (password == null || (password.trim().length() <= 0)) {
        	model.put("errorMessage", "Password is empty. Enter valid Values");
            return "createNewUser";
        }
        else {
        	User newUser = new User();
        	newUser.setStrUserId(userId);
        	newUser.setStrPassword(password);
        	newUser.setStrFirstName(firstName);
			newUser.setStrLastName(lastName);
			userService.createNewUser(newUser);
			
			return "login";
        }
    }
	
	
	
//	@RequestMapping(value="/existingUser", method = RequestMethod.GET)
//    public String showUserList(ModelMap model){
//        
//		List<User> lUsers = userService.fetchAllUsers();
//		System.out.println(" List ofusers "+lUsers.size());
//		
//		model.put("users", lUsers);
//		return "existingUser";
//    }
    
	@RequestMapping(value="/existingUser", method = RequestMethod.GET)
	//@ResponseBody
    public List<User> retrieveUserLisr(ModelMap model){
        
		List<User> users = userService.fetchAllUsers();
		System.out.println(" List ofusers "+users.size());
		
		model.put("users", users);
		return users;
    }
	
	
	@RequestMapping(value = "/deleteUser/{strUserId}", method = RequestMethod.POST)
	public String deleteRecord(ModelMap model, @PathVariable("strUserId") String userId){
		System.out.println("in delete user :: "+userId);
		
		List<User> users = userService.fetchAllUsers();
		User deleteUser = users.get(Integer.parseInt(userId)-1);
		users.remove(Integer.parseInt(userId)-1);
		
		userService.deleteNewUser(deleteUser);
		
		model.put("users", users);		
		return "existingUser";
	}

}