package com.acm.test;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.acm.dao.DepartmentMapper;
import com.acm.dao.UserDeptMapper;
import com.acm.dao.UserMapper;
import com.acm.entity.Department;
import com.acm.entity.User;
import com.acm.entity.UserDept;
import com.acm.utils.Md5Util;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-*.xml" })
public class DaoTest {

	@Autowired
	DepartmentMapper departmentMapper;
	@Autowired
	UserMapper userMapper;

	@Autowired
	SqlSession sqlSession;

	// private ApplicationContext ctx = null;
	@Test
	public void test() {

		// ctx = new ClassPathXmlApplicationContext("spring/spring-dao.xml");
		// System.out.println(userMapper);

		User user = new User();

		user.setUserClass("网络");
		user.setId(7);
		user.setUserId("1");
		Department department = new Department();
		department.setId(1);
		// department.setName("A");

		// 测试 模糊查询
		/*
		 * System.out.println(departmentMapper.selectByDept(department));
		 * 
		 * System.out.println(userMapper.selectByUser(user));
		 */

		// UserMapper 根据部门id 和用户属性 模糊查询测试
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("user", user);
		map.put("dept", department);

		List<User> users = userMapper.selectByDeptIdAndUser(map);
		System.out.println(users);

		/*
		 * Map<String, Object> map = new HashMap<String, Object>();
		 * map.put("user", user); map.put("dept", department); List<Department>
		 * departments = departmentMapper.selectByUserIdAndDept(map);
		 * System.out.println(departments);
		 */

		// suserMapper.deleteByPrimaryKey(12);

	}

	/**
	 * 批量插入数据
	 */
	@Test
	public void batch() {
		/*
		 * DepartmentMapper deptMapper = (DepartmentMapper)
		 * sqlSession.getMapper(DepartmentMapper.class); for(int
		 * i=0;i<1000;i++){ String uid =
		 * UUID.randomUUID().toString().substring(0,4)+i+"ACM编程";
		 * 
		 * Department department = new Department(uid, "123456",null, 1, 2);
		 * department = Md5Util.md5Dept(department);
		 * deptMapper.insertSelective(department);
		 * 
		 * }
		 */
		/*UserMapper userMapper = (UserMapper) sqlSession
				.getMapper(UserMapper.class);
		for (int i = 0; i < 1000; i++) {
			User user = new User("04131503" + i, "计算机网络应用1501", "刘备",
					"123545689", null, 1, "123456");
			user = Md5Util.md5User(user);
			userMapper.insertSelective(user);
		}*/
		
		UserDeptMapper userDeptMapper = (UserDeptMapper) sqlSession
				.getMapper(UserDeptMapper.class);
		for(int i=18;i<700;i++){
			UserDept userDept = new UserDept(i, 16);
			userDeptMapper.insertSelective(userDept);
		}

	}

}
