package com.acm.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acm.entity.Department;
import com.acm.utils.SessionUtil;

@Controller
@RequestMapping("/dept")
public class DepartmentController {

	Department dept = null;
	
	@RequestMapping("deptFrist")
	 public String deptFrist() {
		if(SessionUtil.getSession().getAttribute("dept")==null){
			dept = (Department) SessionUtil.getSession().getAttribute("userOrDept");
		}else{
			dept = (Department) SessionUtil.getSession().getAttribute("dept");
		}
		System.out.println(dept);
		 return "dept";
	 }
	
}
