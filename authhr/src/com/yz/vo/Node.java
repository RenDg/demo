package com.yz.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Node {
	
	private long id;
	private String name ;
	@JsonInclude(Include.NON_NULL)
	private Long  parentId;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getparentId() {
		return parentId;
	}
	public void setparentId(Long parentId) {
		this.parentId = parentId;
	}
	
	
}
