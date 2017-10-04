package com.acm.web;


import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.acm.entity.User;
import com.acm.utils.SessionUtil;


@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping(value="/userFrist",method = {RequestMethod.POST, RequestMethod.GET})
	public String userFrist(Model model) {
    	//System.out.println((User)SessionUtil.getSession().getAttribute("userOrDept"));
		return "user";
	}
	 
}
