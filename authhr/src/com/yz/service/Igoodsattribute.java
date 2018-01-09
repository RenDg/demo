package com.yz.service;

import java.util.Map;

import com.yz.entity.GoodsAttribute;
import com.yz.entity.GoodsType;

public interface Igoodsattribute {

	Map<String, Object> select(Integer page, Integer rows);
	
	boolean add(String itemname, GoodsAttribute attribute, GoodsType goodstype);
	

}
