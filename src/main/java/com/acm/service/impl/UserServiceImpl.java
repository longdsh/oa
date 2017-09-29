package com.acm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acm.dao.UserMapper;
import com.acm.entity.Department;
import com.acm.entity.User;
import com.acm.service.UserService;

@Service
public class UserServiceImpl implements UserService {
    
	@Autowired
	UserMapper userMapper;
	
	@Override
	public User findUserById(Integer id) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(id);
	}


	@Override
	public List<User> findUserByUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectByUser(user);
	}

	@Override
	public List<User> findUserByDeptIdAndUser(Integer deptId,User user) {
		Department department = new Department();
		department.setId(deptId);
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("dept", department);
		map.put("user", user);
		
		// TODO Auto-generated method stub
		return userMapper.selectByDeptIdAndUser(map);
	}

	@Override
	public List<User> findAllUser() {
		// TODO Auto-generated method stub
		return findUserByUser(null);
	}

	@Override
	public void addUser(User user) {
		// TODO Auto-generated method stub
		userMapper.insertSelective(user);

	}

	@Override
	public void deleteUserById(Integer id) {
		userMapper.deleteByPrimaryKey(id);
	}

	@Override
	public void updateUserByUser(User user) {
		// TODO Auto-generated method stub
		userMapper.updateByPrimaryKeySelective(user);

	}

}
