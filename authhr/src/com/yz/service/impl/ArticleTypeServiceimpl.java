package com.yz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yz.dao.ArticleTypeMapper;
import com.yz.entity.ArticleType;
import com.yz.service.IarticletypeService;
@Service
public class ArticleTypeServiceimpl implements IarticletypeService {
	@Autowired
	private ArticleTypeMapper arttypemap;
	
	@Override
	public List<ArticleType> findall() {
		List<ArticleType> list = arttypemap.selectByExample(null);
		return list;
	}
	//ап╠М
	@Override
	public Map<String, Object> list() {
		List<ArticleType> list = arttypemap.selectByExample(null);
		 Map<String, Object> map = new HashMap<>();
		 map.put("total", "");
		 map.put("rows", list);
		
		return map;
	}

}
