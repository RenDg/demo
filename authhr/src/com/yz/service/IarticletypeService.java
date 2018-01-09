package com.yz.service;

import java.util.List;
import java.util.Map;

import com.yz.entity.ArticleType;

public interface IarticletypeService {
	
	List<ArticleType> findall();
	
	Map<String, Object> list();

}
