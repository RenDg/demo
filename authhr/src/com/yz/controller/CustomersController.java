package com.yz.controller;

import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Customers;
import com.yz.service.IcustomersService;
import com.yz.vo.AjaxJson;

@Controller
@RequestMapping("/cus")
public class CustomersController {
	
	@Autowired
	private IcustomersService customersservice; 
	
	@RequestMapping("index")
	public String index(){
		
		return "customers/index";
	}
	//列表
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer rows){
		
		Map<String,Object> map = customersservice.list(page,rows);
		
		return map;
	}
	//添加跳转
	@RequestMapping("toadd")
	public String toadd(){
		
		return "customers/add";
	}
	//添加 修改
	@RequestMapping("add")
	@ResponseBody
	public AjaxJson add_update(Customers customers){
		
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		if(customers.getId()==null){
			flag =customersservice.add(customers);
			json.setMsg("对不起，添加失败！");
		}else{
			flag=customersservice.update(customers);
			json.setMsg("对不起，修改失败！");
		}
		json.setSuccess(flag);
		 return json;
		
	}
	//修改回显
	@RequestMapping("init")
	public String init(String id,Map map){
		
		Customers cus = customersservice.set(id);
		map.put("cus", cus);
		return "customers/add";
	}
	
	@RequestMapping("del")
	@ResponseBody
	public AjaxJson del(String ids){
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		
		flag=customersservice.del(ids);
		json.setMsg("对不起，删除失败！");
		json.setSuccess(flag);
		return json;
		
	}
}
