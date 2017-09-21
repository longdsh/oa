package com.acm.web;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("test")
public class test {
   
	@RequestMapping(value="/login")
	public String add(HttpServletRequest request){
		request.getSession().setAttribute("user", 1);
		return "/login";
	}
}
