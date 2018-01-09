package com.yz.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Resources {
    private Long id;
    private String resName;

    private String url;
    
    private String resType; 
    
    private Long resState;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty("_parentId")
    private Long pid;
    @JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
    private Date createDate;

    private Date updateDate;
    
    
    
    private Long roleid;
    
    
    
    public Long getRoleid() {
		return roleid;
	}

	public void setRoleid(Long roleid) {
		this.roleid = roleid;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getResName() {
        return resName;
    }

    public void setResName(String resName) {
        this.resName = resName == null ? null : resName.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public String getResType() {
        return resType;
    }

    public void setResType(String resType) {
        this.resType = resType == null ? null : resType.trim();
    }

    public Long getResState() {
        return resState;
    }

    public void setResState(Long resState) {
        this.resState = resState;
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
		return "Resources [id=" + id + ", resName=" + resName + ", url=" + url + ", resType=" + resType + ", resState="
				+ resState + ", pid=" + pid + ", createDate=" + createDate + ", updateDate=" + updateDate + "]";
	}
}