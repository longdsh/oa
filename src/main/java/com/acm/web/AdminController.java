package com.acm.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acm.entity.Department;
import com.acm.entity.Message;
import com.acm.entity.User;
import com.acm.service.impl.DepartmentServiceImpl;
import com.acm.service.impl.UserServiceImpl;

/**
* @author 计算机网络软件应用1501 路素飞
* QQ 2512977541
* @version 创建时间：2017年10月1日 下午3:42:27
* 类说明
*/
@Controller
@RequestMapping("/admin")
public class AdminController {
	
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	
	@RequestMapping(value="/adminFrist",method = {RequestMethod.POST, RequestMethod.GET})
	public String adminFrist(Model model) {
		List<Department> departments = departmentServiceImpl.findAllDepartment();
		model.addAttribute("departments", departments);
		return "admin";
	}
	
	@ResponseBody
	@RequestMapping(value="/getAllDept")
	public Message getAllDept() {
		
	    List<Department> departments = departmentServiceImpl.findAllDepartment();
	    return Message.success().add("departments", departments);
	}
}
