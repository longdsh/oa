package com.acm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acm.dao.UserDeptMapper;
import com.acm.entity.UserDept;
import com.acm.service.UserDeptService;

@Service
public class UserDeptServiceImpl implements UserDeptService {

	@Autowired
	UserDeptMapper userDeptMapper;
	@Override
	public void userAddDept(UserDept userDept) {
		// TODO Auto-generated method stub
		userDeptMapper.insert(userDept);

	}

	@Override
	public void userDelectDept(Integer deptId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void DeptDelectUser(Integer userId) {
		// TODO Auto-generated method stub

	}

}
