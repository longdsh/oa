package com.acm.service;

import java.util.List;
import java.util.Map;

import com.acm.entity.Department;
import com.acm.entity.User;

public interface UserService {
	
	public User findUserById(Integer id);//按主键
	;
	
	public long countByUserId(String userId);
	
	public List<User> findUserByUser(User user);
	
	public List<User> findUserByDeptIdAndUser(Integer deptId,User user);//map中放入 department 和 user
	
	public List<User> findAllUser();
	
	public void addUser(User user);
	
	public void deleteUserById(Integer id);
	
	public void updateUserByUser(User user);

}
