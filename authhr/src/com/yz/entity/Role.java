package com.yz.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class Role {
    private Long id;

    private String roleName;

    private String roleDesc;

    private Short roleState;

    private String isAllAuth;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createDate;

    private Date updateDate;
    
    private Long userId ;
    

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc == null ? null : roleDesc.trim();
    }

    public Short getRoleState() {
        return roleState;
    }

    public void setRoleState(Short roleState) {
        this.roleState = roleState;
    }

    public String getIsAllAuth() {
        return isAllAuth;
    }

    public void setIsAllAuth(String isAllAuth) {
        this.isAllAuth = isAllAuth == null ? null : isAllAuth.trim();
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
		return "Role [id=" + id + ", roleName=" + roleName + ", roleDesc=" + roleDesc + ", roleState=" + roleState
				+ ", isAllAuth=" + isAllAuth + ", createDate=" + createDate + ", updateDate=" + updateDate + ", userId="
				+ userId + "]";
	}
}