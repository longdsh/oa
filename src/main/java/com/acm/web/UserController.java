package com.acm.web;

import static org.hamcrest.CoreMatchers.nullValue;

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
import com.acm.entity.UserDept;
import com.acm.service.impl.DepartmentServiceImpl;
import com.acm.service.impl.UserDeptServiceImpl;
import com.acm.service.impl.UserServiceImpl;
import com.acm.utils.Md5Util;
import com.acm.utils.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mchange.v2.log.PackageNames;

@Controller
@RequestMapping("/user")
public class UserController {

	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	UserDeptServiceImpl userDeptServiceImpl;

	List<Department> allDepts = null;

	List<Department> joinDepts = null;

	PageInfo<Department> allPageDept = null;

	PageInfo<Department> joinPageDept = null;

	User user = null;

	/**
	 * 跳转 及初始化信息
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/userFrist")
	public String userFrist(Model model) {
		if(SessionUtil.getSession().getAttribute("user")==null){
		   user = (User) SessionUtil.getSession().getAttribute("userOrDept");
		}else{
		   user = (User) SessionUtil.getSession().getAttribute("user");
		}
		model.addAttribute("user", user);
		return "user";
	}

	/**
	 * 第一次跳转Ajax刷新页面
	 * 
	 * @param allDeptPageNum
	 * @param joinDeptPageNum
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/f5All")
	/* @RequestParam("allDeptPageNum") */
	public Message f5All(Integer allDeptPageNum, String deptName, Model model) {
     //  System.out.println("allDeptPageNum:"+allDeptPageNum);
      // System.out.println("deptName:"+deptName);
		allPageDept = getAllDept(allDeptPageNum,deptName);
		//System.out.println("f5All:"+user);
		return Message.success().add("user",this.user)
				.add("allPageDept", allPageDept);
		// .add("joinPageDept", joinPageDept);

	}
	@ResponseBody
	@RequestMapping(value="/f5Join")
	public Message f5Join(Integer joinDeptPageNum,String deptName,
			Model model) {
		joinPageDept = getJoinDept(joinDeptPageNum,deptName);
		return Message.success().add("user", this.user)
				.add("joinPageDept", joinPageDept);

	}
    
	
	@ResponseBody
	@RequestMapping(value="/userAddDept")
	public Message userAddDept(Integer deptKey) {
		if(userDeptServiceImpl.count(user.getId(), deptKey)){
			return Message.fail().setMsg("已加入此部门");
		}
		Department department = departmentServiceImpl.findDepartmentById(deptKey);
		if(department.getIsRecruit()==0){
			return Message.fail().setMsg("此部门已招满");
		}
		//传入2个主键    类中变量名取名不当0.0
		UserDept userDept = new UserDept(user.getId(), deptKey);
		userDeptServiceImpl.addUserAndDept(userDept);
		return Message.success();
		
	}
	
	@ResponseBody
	@RequestMapping(value="/userRemoveDept")
	public Message userRemoveDept(Integer deptKey){
		
		try {
			userDeptServiceImpl.deleteContact(user.getId(), deptKey);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return Message.fail();
		}
		return Message.success();
	}
	/**
	 * 修改用户信息
	 */
	@ResponseBody
	@RequestMapping(value="/upUserInfo")
	public Message upUserInfo(User user){
		System.out.println(user);
		if(userServiceImpl.countByUserId(user.getUserId())){
			return Message.fail().setMsg("此学号已存在");
		}
		Md5Util.md5User(user);
		try {
			userServiceImpl.updateUserByUser(user);
		} catch (Exception e) {
			// TODO: handle exception
			return Message.fail().setMsg("未知错误");
		}
		this.user = user;
		SessionUtil.getSession().setAttribute("user", user);
		System.out.println("upUserInfo:"+this.user);
		return Message.success().add("user", user);
	}
	
	/*********************************************************************************************/
	/**
	 * 得到加入部门分页数据
	 * 
	 * @param joinDeptPageNum
	 * @return
	 */
	
	private PageInfo<Department> getJoinDept(Integer joinDeptPageNum,String deptName) {
		// TODO Auto-generated method stub
		Department department = new Department();
		department.setName(deptName);
		PageHelper.startPage(joinDeptPageNum, 10);
		// 分页查询
		joinDepts = departmentServiceImpl.findByUserIdAndDepartment(
				user.getId(), department);
		PageInfo<Department> pageInfo = new PageInfo<>(joinDepts);
		return pageInfo;
	}

	/**
	 * 得到所有部门分页数据
	 * 
	 * @param allDeptPageNum
	 * @return
	 */
	private PageInfo<Department> getAllDept(Integer allDeptPageNum,String deptName) {
		// TODO Auto-generated method stub
		PageHelper.startPage(allDeptPageNum, 10);
		Department department = new Department();
		department.setName(deptName);
		allDepts = departmentServiceImpl.findByDepartment(department);
		PageInfo<Department> pageInfo = new PageInfo<>(allDepts);
		return pageInfo;
	}

}
