package com.yz.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class User {
    private Long id;

    private String name;

    private String username;

    private String pwd;

    private String sex;

    private Long age;

    private Long userType;

    private Long deptId;

    private String telephone;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Long usersState;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createDate;

    private Date updateDate;
    
    //²¿ÃÅµÄ
    private Dept dept;
    

	public Dept getDept() {
		return dept;
	}

	public void setDept(Dept dept) {
		this.dept = dept;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd == null ? null : pwd.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    public Long getUserType() {
        return userType;
    }

    public void setUserType(Long userType) {
        this.userType = userType;
    }

    public Long getDeptId() {
        return deptId;
    }

    public void setDeptId(Long deptId) {
        this.deptId = deptId;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone == null ? null : telephone.trim();
    }

    public Long getUsersState() {
        return usersState;
    }

    public void setUsersState(Long usersState) {
        this.usersState = usersState;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }
    
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", username=" + username + ", pwd=" + pwd + ", sex=" + sex
				+ ", age=" + age + ", userType=" + userType + ", deptId=" + deptId + ", telephone=" + telephone
				+ ", usersState=" + usersState + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
    
}