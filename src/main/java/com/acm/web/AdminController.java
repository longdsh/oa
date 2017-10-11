package com.acm.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import com.acm.service.impl.UserDeptServiceImpl;
import com.acm.service.impl.UserServiceImpl;
import com.acm.utils.Md5Util;
import com.acm.utils.SessionUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

/**
 * @author 计算机网络软件应用1501 路素飞 QQ 2512977541
 * @version 创建时间：2017年10月1日 下午3:42:27 类说明
 */
@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	@Autowired
	UserDeptServiceImpl userDeptServiceImpl;

	Department admin = null;
	List<Department> allDepts = null;
	PageInfo<Department> allPageDept = null;

	@RequestMapping(value = "/adminFrist")
	public String adminFrist(Model model) {
		if ((Department) SessionUtil.getSession().getAttribute("admin") == null) {
			admin = (Department) SessionUtil.getSession().getAttribute(
					"userOrDept");
		} else {
			admin = (Department) SessionUtil.getSession().getAttribute("admin");
		}
		System.out.println("adminFrist:"+admin);
		return "admin";
	}

	@ResponseBody
	@RequestMapping(value = "/f5All")
	public Message f5All(Integer allDeptPageNum, String deptName, Model model) {
		allPageDept = getAllDept(allDeptPageNum,deptName);
		System.out.println("f5All:"+admin);
		return Message.success().add("admin",this.admin)
				.add("allPageDept", allPageDept);
		// .add("joinPageDept", joinPageDept);

	}
	
	@ResponseBody
	@RequestMapping(value="/upAdminInfo")
	public Message upAdminInfo(Department department){
		System.out.println("upAdminInfo:"+department);
		if(departmentServiceImpl.countDeptByName(department.getName())!=0){
			return Message.fail();
		}
		admin = Md5Util.md5Dept(department);
		departmentServiceImpl.updateDeparmentByDept(admin);
		SessionUtil.getSession().setAttribute("admin", admin);
		return Message.success().add("admin", admin);
		
	}
	
	@ResponseBody
	@RequestMapping(value="/toUserPage")
	public Message toUserPage(String userId,HttpServletResponse response,HttpServletRequest request) throws IOException{
		
		if(userServiceImpl.countByUserId(userId)){
			User user = userServiceImpl.findUserByUserId(userId);
			SessionUtil.getSession().setAttribute("user", user);
			
			return Message.success().add("path", request.getContextPath()+"/user/userFrist");
		}
		
		return Message.fail();
		
	}
	
	@ResponseBody
	@RequestMapping(value="/addDept")
	public Message addDept(Department department){
		if(departmentServiceImpl.countDeptByName(department.getName())!=0){
			return Message.fail();
		}
		department.setPower(2);
		department = Md5Util.md5Dept(department);
		departmentServiceImpl.addDepartment(department);
		return Message.success();
	}
	
	@ResponseBody
	@RequestMapping(value="/deleteDept")
	public Message deleteDept(Integer deptKey){
		System.out.println("deptKey:"+deptKey);
		departmentServiceImpl.deleteDepartmentById(deptKey);
		userDeptServiceImpl.deleteContact(null, deptKey);
		return Message.success();

		
	}
	@ResponseBody
	@RequestMapping(value="/toDeptPage")
	public Message toDeptPage(Integer deptKey,HttpServletRequest request){
		System.out.println("deptKey:"+deptKey);
		Department dept = departmentServiceImpl.findDepartmentById(deptKey);
		
		System.out.println("toDeptPage:"+dept);
		String path = request.getContextPath()+"/dept/deptFrist";
		SessionUtil.getSession().setAttribute("dept", dept);
		return Message.success().add("path", path);
	}
	
/***************************************************************/	
	/**
	 * 得到所有部门分页数据
	 * 
	 * @param allDeptPageNum
	 * @return
	 */
	private PageInfo<Department> getAllDept(Integer allDeptPageNum,String deptName) {
		// TODO Auto-generated method stub
		PageHelper.startPage(allDeptPageNum, 2);
		Department department = new Department();
		department.setName(deptName);
		allDepts = departmentServiceImpl.findByDepartment(department);
		PageInfo<Department> pageInfo = new PageInfo<>(allDepts);
		return pageInfo;
	}
}

