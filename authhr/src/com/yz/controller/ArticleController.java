package com.yz.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Article;
import com.yz.entity.ArticleType;
import com.yz.entity.Dept;
import com.yz.service.IarticleService;
import com.yz.vo.AjaxJson;
import com.yz.vo.Node;
import com.yz.vo.ZtreeNode;

@Controller
@RequestMapping("article")
public class ArticleController {
	@RequestMapping("index")
	public String index(){
		
		return "article/index";
	}
	@Autowired
	private IarticleService Articleservice;
	
	//列表
	@RequestMapping("list")
	@ResponseBody
	public Map<String ,Object> list(Integer page ,Integer rows ,Integer articleId){
		
	return 	Articleservice.list(page,rows,articleId);
	}
	@RequestMapping("toadd")
	public String toadd(){
		
		return "article/add";
	}
	
	@RequestMapping("add")
	@ResponseBody
	public AjaxJson add(Article article){
		AjaxJson json = new AjaxJson();
		boolean flag =false;
		if(article.getId()==null){
			
			flag = Articleservice.add(article);
			json.setMsg("添加失败 ");
		}else{
			flag = Articleservice.update(article);
			json.setMsg("修改失败");
		}
		
		json.setSuccess(flag);
		return json;
	}
	
	
}
