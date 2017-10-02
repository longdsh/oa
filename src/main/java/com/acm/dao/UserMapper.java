package com.acm.dao;

import com.acm.entity.User;
import com.acm.entity.UserExample;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    long countByExample(UserExample example);

    int deleteByExample(UserExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);
    
    // <!-- 根据部门id 和用户数据模糊查询 -->
    List<User> selectByDeptIdAndUser(Map<String,Object> map);
    //<!-- 根据User信息模糊查询 -->
    List<User> selectByUser(User user);
      
    List<User> selectByExample(UserExample example);

    User selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}