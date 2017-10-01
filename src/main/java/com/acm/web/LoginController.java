package com.acm.web;

import org.apache.shiro.SecurityUtils;
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
	
	//登录跳转
    @RequestMapping(value = "/login", method = {RequestMethod.GET})
    public String loginUI() throws Exception {
        return "../../login";
    }
	
	 @RequestMapping(value = "/login", method = {RequestMethod.POST})
	    public String login(
	    		@RequestParam("userId") String id,
				 @RequestParam("name") String username,
				 @RequestParam("password") String passworde) throws Exception {

	        //Shiro实现登录
	        UsernamePasswordToken token = new UsernamePasswordToken(username,
	                passworde);
	        Subject subject = SecurityUtils.getSubject();

	        //如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
	        subject.login(token);

	        if (subject.hasRole("admin")) {
	            return "redirect:/admin/adminFrist";
	        }else if(subject.hasRole("user")) {
	        	return "redirect:/user/userFrist";
	        }

	        return "/login";
	    }
}
