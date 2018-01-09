package com.yz.service;

import java.util.List;
import java.util.Map;

import com.yz.entity.Article;
import com.yz.entity.ArticleType;
import com.yz.entity.Dept;

public interface IarticleService {

	Map<String, Object> list(Integer page, Integer rows,Integer articleId);

	boolean add(Article article);

	boolean update(Article article);
	



}
