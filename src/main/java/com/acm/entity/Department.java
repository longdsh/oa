package com.acm.entity;

import com.xuxueli.poi.excel.annotation.ExcelField;
import com.xuxueli.poi.excel.annotation.ExcelSheet;

//@ExcelSheet(name = "商户列表")
public class Department {
	@ExcelField(name = "ID")
    private Integer id;
	@ExcelField(name = "部门名")
    private String name;

    private String password;

    private String img;

    private Integer isRecruit;//是否允许加入

    private Integer power;
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img == null ? null : img.trim();
    }

    public Integer getIsRecruit() {
        return isRecruit;
    }

    public void setIsRecruit(Integer isRecruit) {
        this.isRecruit = isRecruit;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }
    

	

	public Department() {
		super();
	}

	public Department(String name, String password, String img,
			Integer isRecruit, Integer power) {
		super();
		this.name = name;
		this.password = password;
		this.img = img;
		this.isRecruit = isRecruit;
		this.power = power;
	}

	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", password="
				+ password + ", img=" + img + ", isRecruit=" + isRecruit
				+ ", power=" + power + " ]";
	}

	
    
    
}