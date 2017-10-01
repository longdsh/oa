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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.acm.entity.User;


@Controller
@RequestMapping("/user")
public class UserController {
    
	 @RequestMapping(value="/login")
	 public String UserLogin(HttpServletRequest request,
			 @RequestParam("userId") String id,
			 @RequestParam("name") String username,
			 @RequestParam("password") String password) {
		 String error = null;
		 System.out.println("id:"+id+"  username:"+username+"  password:"+password);
		 Subject subject = SecurityUtils.getSubject(); 
		 if (!subject.isAuthenticated()) {
	        	// 把用户名和密码封装为 UsernamePasswordToken 对象
	            UsernamePasswordToken token = new UsernamePasswordToken(username, password);
	            token.setRememberMe(true);
	            try {
	            	System.out.println("1. " + token.hashCode());
	            	// 执行登录. 
	                subject.login(token);
	            }catch (UnknownAccountException e) {  
	                error = "用户名/密码错误";  
	            } catch (IncorrectCredentialsException e) {  
	                error = "用户名/密码错误";  
	            } catch (ExcessiveAttemptsException e) {  
	                // TODO: handle exception  
	                error = "登录失败多次，账户锁定10分钟";  
	            } catch (AuthenticationException e) {  
	                // 其他错误，比如锁定，如果想单独处理请单独catch处理  
	                error = "其他错误：" + e.getMessage();  
	            }  
	            if (error != null) {// 出错了，返回登录页面  
	                request.setAttribute("error", error); 
	                System.out.println(error);
	                return "failure";  
	            } else {// 登录成功  
	                return "list";  
	            }  
	            
	        }
		 
		return "list";
		 
	 }
	 @RequestMapping("/register")
	 public String UserRegister(User user) {
		 System.out.println(user);
		 return "list";
	 }
	 
	 //@RequiresRoles("user")
	 @RequiresPermissions( "frist" )
	 @RequestMapping("/frist")
	 public String UserFrist(){
		 System.out.println("user");
		 return "user";
	 }
	 
	 
}
