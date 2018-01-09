package com.yz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yz.dao.GoodsAttributeItemMapper;
import com.yz.dao.GoodsAttributeMapper;
import com.yz.entity.GoodsAttribute;
import com.yz.entity.GoodsAttributeItem;
import com.yz.entity.GoodsType;
import com.yz.service.Igoodsattribute;
@Service
public class Goodsattributeimpl implements Igoodsattribute {
	
	@Autowired
	private GoodsAttributeMapper goodsattrmap;
	@Autowired
	private GoodsAttributeItemMapper itemmap;
	@Override
	public Map<String, Object> select(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<GoodsAttribute> list = goodsattrmap.select();
		PageInfo<GoodsAttribute> pageInfo = new PageInfo<>(list);
		Map<String, Object> map=new HashMap<>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", list);
		return map;
	}
	
	@Override
	public boolean add(String itemname, GoodsAttribute attribute, GoodsType goodstype) {
		
		int i = goodsattrmap.insert(attribute);
		//可选项表
		GoodsAttributeItem item = new GoodsAttributeItem();
		item.setAttrId(attribute.getId());
		item.setItemName(itemname);
		int j = itemmap.insert(item);
		if(i>0&&j>0){
			return true;
		}
		
		return false;
	}

}
