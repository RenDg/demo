package com.yz.entity;

public class GoodsAttributeItem {
    private Integer id;

    private Integer attrId;

    private String itemName;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAttrId() {
        return attrId;
    }

    public void setAttrId(Integer attrId) {
        this.attrId = attrId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName == null ? null : itemName.trim();
    }

	@Override
	public String toString() {
		return "GoodsAttributeItem [id=" + id + ", attrId=" + attrId + ", itemName=" + itemName + "]";
	}
}