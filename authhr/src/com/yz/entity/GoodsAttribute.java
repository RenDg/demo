package com.yz.entity;

public class GoodsAttribute {
    private Integer id;
    
    private Integer typeid;

    private String attrName;

    private Integer sorts;
    
    private String tname;
    
    private String kname;
    
    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTypeid() {
        return typeid;
    }

    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    public String getAttrName() {
        return attrName;
    }

    public void setAttrName(String attrName) {
        this.attrName = attrName == null ? null : attrName.trim();
    }

    public Integer getSorts() {
        return sorts;
    }

    public void setSorts(Integer sorts) {
        this.sorts = sorts;
    }

	public String getTname() {
		return tname;
	}

	public void setTname(String tname) {
		this.tname = tname;
	}

	public String getKname() {
		return kname;
	}

	public void setKname(String kname) {
		this.kname = kname;
	}

	@Override
	public String toString() {
		return "GoodsAttribute [id=" + id + ", typeid=" + typeid + ", attrName=" + attrName + ", sorts=" + sorts
				+ ", tname=" + tname + ", kname=" + kname + "]";
	}

    
		
}