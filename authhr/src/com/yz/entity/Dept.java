package com.yz.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Dept {
    private Long id;
    
    private String deptNo;
    
   // @JsonProperty("text")
    private String deptName;
    
    private Short deptOrder;
    
    private String iconCls;

    private String address;
    //_parentId
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("_parentId")
    private Long pid;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createDate;

    private Date updateDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeptNo() {
        return deptNo;
    }

    public void setDeptNo(String deptNo) {
        this.deptNo = deptNo == null ? null : deptNo.trim();
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName == null ? null : deptName.trim();
    }

    public Short getDeptOrder() {
        return deptOrder;
    }

    public void setDeptOrder(Short deptOrder) {
        this.deptOrder = deptOrder;
    }

    public String getIconCls() {
        return iconCls;
    }

    public void setIconCls(String iconCls) {
        this.iconCls = iconCls == null ? null : iconCls.trim();
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
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
		return "Dept [id=" + id + ", deptNo=" + deptNo + ", deptName=" + deptName + ", deptOrder=" + deptOrder
				+ ", iconCls=" + iconCls + ", address=" + address + ", pid=" + pid + ", createDate=" + createDate
				+ ", updateDate=" + updateDate + "]";
	}
    
}