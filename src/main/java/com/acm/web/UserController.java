package com.acm.web;


import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.acm.entity.Department;
import com.acm.entity.Message;
import com.acm.entity.User;
import com.acm.service.impl.DepartmentServiceImpl;
import com.acm.utils.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mchange.v2.log.PackageNames;


@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	
	PageInfo<Department> pageDept = null;
	
	int allDeptPageNum = 1; //保存查询出所有部门信息的当前页码
	int joinDeptPageNum = 1;//保存已加入部门的当前页码
	
	/**
	 * 初始化信息
	 * @param model
	 * @return
	 */
    @RequestMapping(value="/userFrist")
	public String userFrist(Model model) {
    	User user = (User) SessionUtil.getSession().getAttribute("userOrDept");
    	
    	System.out.println(user);
    	model.addAttribute("user", user);
    	model.addAttribute("allDeptPageNum", allDeptPageNum);
    	model.addAttribute("joinDeptPageNum", joinDeptPageNum);
		return "user";
	}
    
    
    /**
     * 分页查出部门信息 及模糊查询
     * @param department
     * @return
     *   
     */
    @ResponseBody
    @RequestMapping(value="getDept")
    public Message getDept(Department department,Model model) {
    	allDeptPageNum = 1;
    	model.addAttribute("allDeptPageNum", allDeptPageNum);
    	PageHelper.startPage(allDeptPageNum, 8);

    	List<Department> departments = departmentServiceImpl.findByDepartment(department);

    	pageDept = new PageInfo<Department>(departments);

    	return Message.success().add("pageDept", pageDept);
    	
    }
    
   /**
    * 翻页
    * @param allDeptPageNum
    * @param model
    * @return
    */
    @ResponseBody
    @RequestMapping(value="/toAllDeptPageNum")
    public Message toAllDeptPageNum(Integer allDeptPageNum,Model model) {
    	//设置当前页是第几页  
    	model.addAttribute("allDeptPageNum", allDeptPageNum);
    	PageHelper.startPage(allDeptPageNum, 8);
		return Message.success().add("pageDept", pageDept);
    	
    }
	 
}
