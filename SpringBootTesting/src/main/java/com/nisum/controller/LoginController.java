package com.nisum.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nisum.domain.User;
import com.nisum.services.LoginService;

@Controller
public class LoginController {

	@Autowired
	LoginService loginService;

	@GetMapping(value="/")
	public String welcomePage() {
		//return "redirect:loginOptions.html";
		return "loginOptions";
	}
	
	@RequestMapping(value="/login", method = RequestMethod.GET)
	public String showLoginPage(ModelMap model){
		//return "redirect:login.html";
		return "login";
	}


	@RequestMapping(value="/login", method = RequestMethod.POST)
//	@RequestMapping(value="/login")
	public String showWelcomePage(ModelMap model, @RequestParam String name, @RequestParam String password){
		System.out.println(" Kishore #######"+name);

		if(StringUtils.isNotBlank(name) && StringUtils.isNotBlank(password)) {
			User user = loginService.validateUserLogin(name, password);

			if (user != null ) {
				System.out.println(" Kishore Testing  "+user.getStrFirstName());
				model.put("name", "Welcome "+user.getStrFirstName()+" , "+user.getStrLastName());
				
				System.out.println(" Testing  "+model.get("name"));
				//return "redirect:loginHome.html";
				return "loginHome";
			}
			else {
				System.out.println(" Invalid credentials");
				model.put("errorMessage", "Invalid credentials");
			}
		}
		return "login";
	}
	
	
	@RequestMapping(value="/loginHome", method = RequestMethod.GET)
    public String showLoginHome(ModelMap model){
        return "loginHome";
    }

	
}