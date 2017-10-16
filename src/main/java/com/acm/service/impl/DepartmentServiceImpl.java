package com.acm.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acm.dao.DepartmentMapper;
import com.acm.entity.Department;
import com.acm.entity.DepartmentExample;
import com.acm.entity.DepartmentExample.Criteria;
import com.acm.entity.User;
import com.acm.service.DepartmentService;


@Service
public class DepartmentServiceImpl implements DepartmentService {

	@Autowired
	DepartmentMapper departmentMapper;
	@Override
	public Department findDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		return departmentMapper.selectByPrimaryKey(id);
	}
    
	@Override
	public long countDeptByName(String deptName) {
		// TODO Auto-generated method stub
		DepartmentExample example = new DepartmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(deptName);
		return departmentMapper.countByExample(example);
	}
	
	@Override
	public Department findDepartmentByName(String deptName) {
		// TODO Auto-generated method stub
		DepartmentExample example = new DepartmentExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(deptName);
		List<Department> departments = departmentMapper.selectByExample(example);
		if(departments.size()==0) {
			return null;
		}
		return departments.get(0);
	}


	@Override
	public List<Department> findByDepartment(Department department) {
		// TODO Auto-generated method stub
		return departmentMapper.selectByDept(department);
	}

	@Override
	public List<Department> findByUserIdAndDepartment(Integer userId,Department department) {
		// TODO Auto-generated method stub
		if(department==null) {
			department = new Department();
		}
		
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
		return findByDepartment(null);
	}

	@Override
	public void addDepartment(Department department) {
		// TODO Auto-generated method stub
		departmentMapper.insertSelective(department);

	}

	@Override
	public void deleteDepartmentById(Integer id) {
		// TODO Auto-generated method stub
		departmentMapper.deleteByPrimaryKey(id);

	}

	@Override
	public void updateDeparmentByDept(Department department) {
		// TODO Auto-generated method stub
		departmentMapper.updateByPrimaryKeySelective(department);

	}


	
	
}
