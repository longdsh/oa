package com.acm.entity;

import com.xuxueli.poi.excel.annotation.ExcelField;

public class User {
    private Integer id;//主键
    
    @ExcelField(name = "学号")
    private String userId;//学号
    @ExcelField(name = "班级")
    private String userClass;//班级
    @ExcelField(name = "姓名")
    private String name;
    @ExcelField(name = "手机")
    private String phone;

    private String img;//头像

    private Integer power;//权限  分 1 2 3 

    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public String getUserClass() {
        return userClass;
    }

    public void setUserClass(String userClass) {
        this.userClass = userClass == null ? null : userClass.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    
	public User() {
		super();
	}
   
	
	
	public User(String userId, String userClass, String name, String phone,
			String img, Integer power, String password) {
		super();
		this.userId = userId;
		this.userClass = userClass;
		this.name = name;
		this.phone = phone;
		this.img = img;
		this.power = power;
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", userId=" + userId + ", userClass="
				+ userClass + ", name=" + name + ", phone=" + phone + ", img="
				+ img + ", power=" + power + ", password=" + password + "]";
	}
    
    
}