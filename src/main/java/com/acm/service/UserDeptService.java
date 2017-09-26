package com.acm.service;

import com.acm.entity.UserDept;

public interface UserDeptService {
	
	
	//用户加入某部门
	public void userAddDept(UserDept userDept);
	//用户退出某部门
	public void userDelectDept(Integer deptId);
	
	//部门移除用户
	public void DeptDelectUser(Integer userId);

}
