package com.nisum.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.nisum.domain.User;
import com.nisum.repository.GoogleSignInService;


@Controller
public class GoogleSignInController {

	@Autowired
	private GoogleSignInService signService;

	User user = null;


	@GetMapping
	@RequestMapping("/OAuth2Callback")
	public String callback(@RequestParam String code) {
		String tokenResponse;
		User user=null;
		if (StringUtils.isNotBlank(code)) {
			signService.testProperties();
			tokenResponse = signService.callTokenAPI(code);
			user = signService.callUserInfoAPI(tokenResponse);
			this.user = user;
			return "redirect:loginHome.html";
		}
		System.out.println("blank value of token "+code);
		return "redirecting code value is not authenticated";
	}


	

}
