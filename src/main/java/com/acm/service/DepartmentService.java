package com.acm.service;

import java.util.List;
import java.util.Map;

import com.acm.entity.Department;

public interface DepartmentService {
	
	public Department findDepartmentById(Integer id);
	
	public List<Department> findByDepartment(Department department);//模糊查询 差全部传入空值
	
	public List<Department> findByUserIdAndDepartment(Map<String, Object> map);
	
	public List<Department> findAllDepartment();
	
	public void addDepartment(Department department);
	
	public void delectDepartmentById(Integer id);
	
	public void updataDeparmentByDept(Department department);

}
