package com.yz.entity;

public class GoodsType {
    private Integer typeid;

    private Integer pid;

    private String typeName;
    
    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
    }

	@Override
	public String toString() {
		return "GoodsType [typeid=" + typeid + ", pid=" + pid + ", typeName=" + typeName + "]";
	}
}