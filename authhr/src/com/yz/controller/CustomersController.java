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
	//�б�
	@RequestMapping("list")
	@ResponseBody
	public Map<String,Object> list(Integer page,Integer rows){
		
		Map<String,Object> map = customersservice.list(page,rows);
		
		return map;
	}
	//�����ת
	@RequestMapping("toadd")
	public String toadd(){
		
		return "customers/add";
	}
	//��� �޸�
	@RequestMapping("add")
	@ResponseBody
	public AjaxJson add_update(Customers customers){
		
		AjaxJson json=new AjaxJson();
		boolean flag=false;
		if(customers.getId()==null){
			flag =customersservice.add(customers);
			json.setMsg("�Բ������ʧ�ܣ�");
		}else{
			flag=customersservice.update(customers);
			json.setMsg("�Բ����޸�ʧ�ܣ�");
		}
		json.setSuccess(flag);
		 return json;
		
	}
	//�޸Ļ���
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
		json.setMsg("�Բ���ɾ��ʧ�ܣ�");
		json.setSuccess(flag);
		return json;
		
	}
}
