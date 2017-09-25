package com.acm.test;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.acm.dao.UserMapper;
import com.acm.entity.User;





@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/spring-*.xml"})
public class DaoTest {
	
	/*@Autowired
	DepartmentMapper departmentMapper;*/
	@Autowired
	UserMapper userMapper;
	/*@Autowired
	CustomerMapper customerMapper;*/

	@Autowired
	SqlSession sqlSession;

	//private ApplicationContext ctx = null;
	@Test
	public void test() {
		
		//ctx = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
		System.out.println(userMapper);
	    User user = new User();
	    user.setName("方法");
		int port = userMapper.insertSelective(user);
		System.out.println(port);
	    
	    User user2 = userMapper.selectByPrimaryKey(2);
	    System.out.println(user2.getName());
		
		/*Customer customer = customerMapper.selectByPrimaryKey("0413170302");
		System.out.println(customer.getName());*/
		
		
	}

}
