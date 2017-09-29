package com.acm.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;









import com.acm.dao.DepartmentMapper;
import com.acm.dao.UserMapper;
import com.acm.entity.Department;
import com.acm.entity.User;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class DaoTest {
	
	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	UserMapper userMapper;
	

	@Autowired
	SqlSession sqlSession;

	//private ApplicationContext ctx = null;
	@Test
	public void test() {
		
		//ctx = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
		//System.out.println(userMapper);
	   
		
		User user = new User();
		
	    user.setUserClass("网络"); 
	    user.setId(7);
	    user.setUserId("1");
	    Department department = new Department();
	    department.setId(1);
	   // department.setName("A");
	    
	    //测试 模糊查询
	   /* System.out.println(departmentMapper.selectByDept(department));
	    
	    System.out.println(userMapper.selectByUser(user));*/

	     //UserMapper 根据部门id 和用户属性 模糊查询测试
	   Map<String, Object> map = new HashMap<String, Object>();
	    map.put("user", user);
	    map.put("dept", department);
	    
	    List<User> users= userMapper.selectByDeptIdAndUser(map);
	    System.out.println(users);
	    
	   /* Map<String, Object> map = new HashMap<String, Object>();
	    map.put("user", user);
	    map.put("dept", department);
	    List<Department> departments = departmentMapper.selectByUserIdAndDept(map);
	    System.out.println(departments);*/
	    
	    //suserMapper.deleteByPrimaryKey(12);
	    
	    
	  
		
		
	}

}
