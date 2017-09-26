package com.acm.service;

import java.util.List;
import java.util.Map;

import com.acm.entity.User;

public interface UserService {
	
	public User findUserById(Integer id);//按主键
	
	public User findUserByUserId(String userId);//按学号查
	
	public List<User> findUserByUser(User user);
	
	public List<User> findUserByDeptIdAndUser(Map<String, Object> map);//map中放入 department 和 user
	
	public List<User> findAllUser();
	
	public void addUser(User user);
	
	public void delectUser(Integer id);
	
	
	
	
	
	

}
