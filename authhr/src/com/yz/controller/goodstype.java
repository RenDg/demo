package com.yz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Dept;
import com.yz.entity.GoodsType;
import com.yz.service.IgoodsType;
import com.yz.vo.Node;

@Controller
@RequestMapping("goodstype")
public class goodstype {
	
	
	@Autowired
	private IgoodsType goodstype ;
	
	@RequestMapping("tree")
	@ResponseBody
	public List<Node> list (){
		List<GoodsType> typelist = goodstype.findall();
		List<Node> nodelist = new ArrayList<Node>();
		 Node node = null;
		 for (GoodsType type : typelist) {
			 node = new Node();
			 node.setId(type.getTypeid());
			 node.setName(type.getTypeName());
			 node.setparentId(new Long(type.getPid()));
			 nodelist.add(node);
		}
		return nodelist;
	}
	
	
}
