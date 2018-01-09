package com.yz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Article;
import com.yz.entity.ArticleType;
import com.yz.service.IarticletypeService;
import com.yz.vo.Node;
import com.yz.vo.ZtreeNode;

@RequestMapping("article_type")
@Controller
public class ArticleTypeController {
	@Autowired
	private IarticletypeService arttype; 
	
	// 文章信息的v_tree 树
	@RequestMapping("/v_tree")
	@ResponseBody
	public List<ZtreeNode> aree(Integer deptId){
		List<ZtreeNode>  ztreenodelist = new ArrayList<ZtreeNode>();
		List<ArticleType> arttypelist = arttype.findall();
		ZtreeNode ztreenode=null;
		if(arttypelist!=null&&!arttypelist.isEmpty()){
			for (ArticleType articletype : arttypelist) {
				ztreenode = new ZtreeNode();
				ztreenode.setId(articletype.getTypeId() );
				ztreenode.setPid(articletype.getPid());
				ztreenode.setName(articletype.getTypeName());
				ztreenodelist.add(ztreenode);
			}
		}
		return ztreenodelist;
	}
	//所属栏目
	@RequestMapping("tree")
	@ResponseBody
	public List<Node> list (){
		List<ArticleType> articletylist = arttype.findall();
		List<Node> nodelist = new ArrayList<Node>();
		 Node node = null;
		 for (ArticleType ar : articletylist) {
			 node = new Node();
			 node.setId(ar.getTypeId());
			 node.setName(ar.getTypeName());
			 node.setparentId(ar.getPid());
			 nodelist.add(node);
		}
		return nodelist;
	}
	
	@RequestMapping("index")
	public String index(){
		
		return "articletype/index";
	}
	
	
	// 列表
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list1(){
		
		return arttype.list();
	}
}
