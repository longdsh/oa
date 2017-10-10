package com.acm.service;

import com.acm.entity.UserDept;

public interface UserDeptService {
	
	
	//用户加入某部门
	public void addUserAndDept(UserDept userDept);
	//删除中间表数据
	public void deleteContact(Integer userId, Integer deptId);
	
	public boolean count(Integer userKey,Integer deptKey);
}
