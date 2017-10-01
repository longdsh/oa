package com.acm.web;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dept")
public class DepartmentController {

	@RequiresPermissions( "frist" )
	//@RequiresRoles("admin")
	@RequestMapping("/frist")
	public String deptFrist() {
		System.out.println("dept");
		return "dept";
	}
}
