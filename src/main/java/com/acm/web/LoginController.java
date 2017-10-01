package com.acm.web;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
* @author 计算机网络软件应用1501 路素飞
* QQ 2512977541
* @version 创建时间：2017年10月1日 下午3:39:17
* 类说明
*/
@Controller
@RequestMapping("/loginUri")
public class LoginController {
	
	
	
	 @RequestMapping(value = "/login", method = {RequestMethod.POST})
	    public String login(
	    		@RequestParam("userId") String id,
				 @RequestParam("name") String username,
				 @RequestParam("password") String passworde) throws Exception {

	        //Shiro实现登录
	        UsernamePasswordToken token = new UsernamePasswordToken(username,
	                passworde);
	        Subject subject = SecurityUtils.getSubject();
	        
	        String error = null;
	        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
	        try {
	        	subject.login(token);
			} catch (UnknownAccountException e) {  
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
	            //request.setAttribute("error", error);  
	            return "failure";  
	        }
	        if (subject.hasRole("admin")) {
	            return "redirect:/admin/adminFrist";
	        }else if(subject.hasRole("user")) {
	        	return "redirect:/user/userFrist";
	        }else {
	        	return "failure"; 
	        }
	    }
	 
	
}
