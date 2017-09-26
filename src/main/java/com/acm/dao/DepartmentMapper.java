package com.acm.dao;

import com.acm.entity.Department;
import com.acm.entity.DepartmentExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface DepartmentMapper {
    long countByExample(DepartmentExample example);

    int deleteByExample(DepartmentExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Department record);

    int insertSelective(Department record);
    
    // <!-- 根据用户id查询出用户加入的部门 和 根据部门名的模糊查询 -->
    List<Department> selectByUserIdAndDept(Map<String, Object> map);
    
    List<Department> selectByExample(DepartmentExample example);

    Department selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByExample(@Param("record") Department record, @Param("example") DepartmentExample example);

    int updateByPrimaryKeySelective(Department record);

    int updateByPrimaryKey(Department record);
}