	package com.yz.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Dept;
import com.yz.entity.User;
import com.yz.service.IuserService;
@Controller
@RequestMapping("user")
public class UserController {
	
	@RequestMapping("index")
	public String index(){
		
		return "user/index";
	}
	
	@Autowired
	private IuserService userService; 
	
	//�б�ӷ�ҳ
	@RequestMapping("list")
	@ResponseBody
	public Map<String ,Object> list(Integer page ,Integer rows ,Integer deptId,
			@RequestParam(required=false) String name,
			String endDate , String firstDate){
		
		 Map<String, Object> list = userService.list(page ,rows,deptId,name,firstDate,endDate);
		 
		 return list ;
	}
	
	
	//  ��� ��ת
	@RequestMapping("toadd")
	public String usertoadd(){
		
		return "user/add";
	}
	
	//���
	@RequestMapping("add")
	@ResponseBody
	public Integer add_update(User user ,Long[] roleIds){
		return 	userService.update(user,roleIds);
	}
	
	//�޸Ļ���
	@RequestMapping("init1")
	public String toupdate(String id ,Map map ){
		User user = userService.set(id);
		map.put("uuser", user);
		return "user/add";
	}
	
	//ɾ��
	@RequestMapping("del")
	@ResponseBody
	public Integer del (String  ids ){
		return 	userService.del(ids);
	}
	
	//��Ȩ��ת
	@RequestMapping("v_auth")
	public String v_auth(Integer userId, Map map){
		 map.put("userId",userId);
		return "user/auth";
	}
	
	//����Ȩ��
	@RequestMapping("o_auth")
	@ResponseBody
	public Integer o_auth(Integer userid , String resIds ){
		return userService.addauth(userid,resIds);
	}
}
