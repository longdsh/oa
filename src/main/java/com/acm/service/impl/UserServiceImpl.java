package com.acm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acm.dao.UserMapper;
import com.acm.entity.Department;
import com.acm.entity.User;
import com.acm.entity.UserExample;
import com.acm.entity.UserExample.Criteria;
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
	public boolean countByUserId(String userId) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		return userMapper.countByExample(example)>0?true:false;

	}
	
	@Override
	public User findUserByUserId(String userId) {
		// TODO Auto-generated method stub
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andUserIdEqualTo(userId);
		List<User> users = userMapper.selectByExample(example);
		if(users.size()==0) {
			return null;
		}
		return users.get(0);
	}

	@Override
	public List<User> findUserByUser(User user) {
		// TODO Auto-generated method stub
		return userMapper.selectByUser(user);
	}

	/**
	 * user 可为空
	 */
	@Override
	public List<User> findUserByDeptIdAndUser(Integer deptId,User user) {
		if(user==null){
			user = new User();
		}
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
