package com.acm.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acm.dao.UserMapper;
import com.acm.entity.Department;
import com.acm.entity.User;
import com.acm.service.impl.DepartmentServiceImpl;
import com.acm.service.impl.UserServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class ServiceTest {

	@Autowired
	UserServiceImpl userServiceImpl;
	@Autowired
	DepartmentServiceImpl departmentServiceImpl;
	
	
	
	List<User> users;
	
	List<Department> departments;
	User user;
	Department department;
	@Test
	public void test() {
		System.out.println(userServiceImpl);
	    System.out.println(userServiceImpl.findUserById(7));
		
		users =  userServiceImpl.findAllUser();
		System.out.println(users);
		
		//userServiceImpl.deleteUserById(15);
		
		user = new User("111111", "网络1501", "lusufei", "172", "d://1.jpg", 1, "1224647asda");
		//userServiceImpl.addUser(user);
		//user = new User();
		//user.setUserId("1");
	//	users = userServiceImpl.findUserByDeptIdAndUser(1, user);
		users = userServiceImpl.findUserByUser(user);
		System.out.println(users);
		
		
	}
	
	@Test
	public void deptTest(){
		department = new Department();
		//department.setName("A");
		departments = departmentServiceImpl.findByUserIdAndDepartment(7, department);
		System.out.println(departments);
		
	} 
	

}