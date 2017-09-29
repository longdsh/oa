package com.acm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acm.dao.DepartmentMapper;
import com.acm.entity.Department;
import com.acm.entity.User;
import com.acm.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;
	@Override
	public Department findDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Department> findByUserIdAndDepartment(Integer userId,Department department) {
		// TODO Auto-generated method stub
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		User user = new User();
		user.setId(userId);
		map.put("user", user);
		map.put("dept", department);
		return departmentMapper.selectByUserIdAndDept(map);
	}

	@Override
	public List<Department> findAllDepartment() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void addDepartment(Department department) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delectDepartmentById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateDeparmentByDept(Department department) {
		// TODO Auto-generated method stub

	}

}