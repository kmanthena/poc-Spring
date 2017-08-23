package com.nisum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class BasicController {

  @RequestMapping("/")
  @ResponseBody
  public String index() {
	  System.out.println(" Inside controller ");
    return "Hello World!!!";
  }

}