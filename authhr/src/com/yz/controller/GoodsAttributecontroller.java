package com.yz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.GoodsAttribute;
import com.yz.entity.GoodsAttributeItem;
import com.yz.entity.GoodsType;
import com.yz.service.IgoodsType;
import com.yz.service.Igoodsattribute;
import com.yz.vo.AjaxJson;
import com.yz.vo.Node;

@Controller
@RequestMapping("attribute")
public class GoodsAttributecontroller {
	
	@RequestMapping("index")
	public String list(){
		
		return  "attribute/index";
	}
	
	@Autowired
	private Igoodsattribute goodsattribute;
	
	@RequestMapping("list")
	@ResponseBody
	public Map<String ,Object> list(Integer page ,Integer rows){
		
		
		return goodsattribute.select(page,rows);
	}
	@RequestMapping("toadd")
	public String toadd(){
		
		return "attribute/add";
	}
	@RequestMapping("add")
	@ResponseBody
	public AjaxJson add(String itemname ,GoodsAttribute attribute,GoodsType goodstype){
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		
		if(attribute.getId()==null){
			flag = goodsattribute.add(itemname,attribute,goodstype);
			json.setMsg("对不起，添加失败！");
		}else{
			
		}
		json.setSuccess(flag);
		return json;
	}
	

}
