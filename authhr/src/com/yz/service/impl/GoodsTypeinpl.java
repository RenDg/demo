package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yz.dao.GoodsTypeMapper;
import com.yz.entity.GoodsType;
import com.yz.service.IgoodsType;
@Service
public class GoodsTypeinpl implements IgoodsType {
	
	@Autowired
	private GoodsTypeMapper goodstypemap;
	@Override
	public List<GoodsType> findall() {
		 List<GoodsType> list = goodstypemap.selectByExample(null);
		
		return list;
	}

}
