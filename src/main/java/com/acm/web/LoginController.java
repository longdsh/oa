package com.acm.web;

import static org.hamcrest.CoreMatchers.nullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acm.entity.Department;
import com.acm.entity.User;
import com.acm.service.impl.DepartmentServiceImpl;
import com.acm.service.impl.UserServiceImpl;
import com.acm.utils.Md5Util;

/**
 * @author 计算机网络软件应用1501 路素飞 QQ 2512977541
 * @version 创建时间：2017年10月1日 下午3:39:17 类说明
 */
@Controller
@RequestMapping("/loginUri")
public class LoginController {

	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	
	@RequestMapping("/toLogin")
	public String toLogin(){
		return "../../login";
	}
	
	/**
	 * 
	 * @param userId
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/login", method = { RequestMethod.POST })
	public String login(@RequestParam("userId") String userId, @RequestParam("name") String username,
			@RequestParam("password") String password) throws Exception {
		
		return deptOrUser(userId, username, password);

	}
	
	/**
	 * 
	 * @param user
	 * @param request
	 * @return
	 */

	// @ResponseBody
	@RequestMapping(value = "/register")
	public String register(User user) {
		System.out.println(user);
        
		Session session = getSession();
		// 判断是否存在id
		String error = null;

		if (userServiceImpl.countByUserId(user.getUserId())) {
			error = "学号已存在";
			session.setAttribute("user", user);
			session.setAttribute("error", error);
			return "../../login";
		}
		//md5加密前先取出原密码
		String password = user.getPassword();
		// 加密
		user = Md5Util.md5User(user);
		// 数据库中存入取出 再添加角色
		userServiceImpl.addUser(user);
		user = userServiceImpl.findUserByUserId(user.getUserId());
		session.setAttribute("userOrDept", user);
		session.setAttribute("role", "user");
		//request.setAttribute("userOrDept", user);
		return role(user.getUserId(), user.getName(), password);

	}

/*************************************************************************************************/	
	/**
	 * 
	 * @param userId
	 * @param username
	 * @param password
	 * @param request
	 * @return
	 */
	//登录角色判断
	public String deptOrUser(String userId, String username, String password) {
		String error = null;
		Session session = getSession();
		if (userId.equals("manage")) {
			if (departmentServiceImpl.countDeptByName(username) == 0) {
				error = "用户不存在";
				session.setAttribute("error", error);
				return "../../login";
			}
			Department department = departmentServiceImpl.findDepartmentByName(username);
			System.out.println("deptOrUser:"+department);
			// 如果部门登录 以部门名为盐值
			session.setAttribute("userOrDept", department);
			session.setAttribute("role", "dept");
			return role(username, userId, password);
		} else {
			if (!userServiceImpl.countByUserId(userId)) {
				error = "用户不存在";
				session.setAttribute("error", error);
				return "../../login";
			}
			User user = userServiceImpl.findUserByUserId(userId);
			session.setAttribute("userOrDept", user);
			session.setAttribute("role", "user");
			return role(userId, username, password);
		}
	}
	/**
	 * 
	 * @param userIdOrDeptName
	 * @param username
	 * @param password
	 * @return
	 */
	public String role(String userIdOrDeptName, String username, String password) {
		// Shiro实现登录
		UsernamePasswordToken token = new UsernamePasswordToken(userIdOrDeptName, password);
		Subject subject = SecurityUtils.getSubject();
		Session session = getSession();
		String error = null;
		// 如果获取不到用户名就是登录失败，但登录失败的话，会直接抛出异常
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
			error = "其他错误：";
		}
		if (error != null) {// 出错了，返回登录页面
			session.setAttribute("error", error);
			return "../../login";
		}
		if (subject.hasRole("admin")) {

			return "redirect:/admin/adminFrist";
		} else if (subject.hasRole("dept")) {

			return "redirect:/dept/deptFrist";
		} else if (subject.hasRole("user")) {

			return "redirect:/user/userFrist";
		} else {
			return "../../login";
		}
	}
	
	public Session getSession() {
		Subject subject = SecurityUtils.getSubject();
        Session session = subject.getSession();
        return session;
	}

}
