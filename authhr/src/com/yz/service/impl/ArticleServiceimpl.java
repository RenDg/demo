package com.yz.service.impl;


import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yz.dao.ArticleMapper;
import com.yz.entity.Article;
import com.yz.entity.ArticleExample;
import com.yz.entity.ArticleExample.Criteria;
import com.yz.service.IarticleService;
@Service
public class ArticleServiceimpl implements IarticleService {
	@Autowired
	private ArticleMapper articlemap;
	//列表
	@Override
	public Map<String, Object> list(Integer page, Integer rows,Integer articleId) {
		
		PageHelper.startPage(page, rows);
		ArticleExample example = new	ArticleExample();
		Criteria criteria = example.createCriteria();
		// 根据ID查询类型
		if(articleId!=null&&articleId!=0){
			criteria.andTypeIdEqualTo(new Long(articleId));
		}
		List<Article> list = articlemap.selectByExample(example);
		PageInfo<Article> PageInfo = new PageInfo<>(list);
		Map<String, Object> map =new HashMap<>();
		map.put("total", PageInfo.getTotal());
		map.put("rows", list);
		return map;
	}
	@Override
	public boolean add(Article article) {
		article.setCreateDate(new Date());
		int i = articlemap.insertSelective(article);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public boolean update(Article article) {
		article.setUpdateDate(new Date());
		int i = articlemap.updateByPrimaryKeySelective(article);
		if(i>0){
			return true;
		}
		
		return false;
	}

}
