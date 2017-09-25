package com.acm.dao;

import com.acm.entity.UserDept;
import com.acm.entity.UserDeptExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserDeptMapper {
    long countByExample(UserDeptExample example);

    int deleteByExample(UserDeptExample example);

    int insert(UserDept record);

    int insertSelective(UserDept record);

    List<UserDept> selectByExample(UserDeptExample example);

    int updateByExampleSelective(@Param("record") UserDept record, @Param("example") UserDeptExample example);

    int updateByExample(@Param("record") UserDept record, @Param("example") UserDeptExample example);
}