package com.nisum.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.nisum.domain.Message;
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
        
		String strErrorResponse = userService.createUser(userId, password, firstName, lastName);
		
        if ( strErrorResponse == null){
        	model.put("errorMessage", strErrorResponse);
            return "createNewUser";
        }
        else {
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
    
	@RequestMapping(value="/existing-User", method = RequestMethod.GET)
	@ResponseBody
    public List<User> retrieveUserLisr(ModelMap model){
        
		List<User> users = userService.fetchAllUsers();
		System.out.println(" List ofusers "+users.size());
		
		//model.put("users", users);
		return users;
    }
	
	@RequestMapping(value="/existingUser", method = RequestMethod.GET)
    public String showTest(){
        return "existingUser";
    }
	
	@RequestMapping(value = "/deleteUser/{strUserId}", method = RequestMethod.POST)
	@ResponseBody
	public Message deleteRecord(@PathVariable("strUserId") String userId){
		System.out.println("in delete user :: "+userId);
		userService.deleteNewUser(userId);
		
		return new Message("Success");
	}

}