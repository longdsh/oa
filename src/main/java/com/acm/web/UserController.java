package com.acm.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class UserController {
    
	 @RequestMapping("/login")
	 public String UserLogin() {
		 System.out.println(111111);
		return "login";
		 
	 }
}
