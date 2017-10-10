package com.acm.entity;

public class UserDept {
    private Integer userId;

    private Integer deptId;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getDeptId() {
        return deptId;
    }

    public void setDeptId(Integer deptId) {
        this.deptId = deptId;
    }

	public UserDept(Integer userId, Integer deptId) {
		super();
		this.userId = userId;
		this.deptId = deptId;
	}

	public UserDept() {
		super();
	}
	
	
    
    
}