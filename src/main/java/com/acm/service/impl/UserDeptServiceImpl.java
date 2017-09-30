package com.acm.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acm.dao.UserDeptMapper;
import com.acm.entity.UserDept;
import com.acm.entity.UserDeptExample;
import com.acm.entity.UserDeptExample.Criteria;
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
	public void deleteContact(Integer userId, Integer deptId) {
		// TODO Auto-generated method stub
		UserDeptExample example = new UserDeptExample();
		Criteria criteria = example.createCriteria();
		if (userId != null) {
			criteria.andDeptIdEqualTo(deptId).andUserIdEqualTo(userId);
		} else {
			criteria.andDeptIdEqualTo(deptId);
		}
		userDeptMapper.deleteByExample(example);
	}
	
}
