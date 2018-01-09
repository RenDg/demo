package com.yz.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.User;
import com.yz.service.IuserService;
import com.yz.vo.AjaxJson;

@Controller
@RequestMapping("login")
public class LoginController {
	
	@RequestMapping("index")
	public String index(){
		return "login";
	}
	@RequestMapping("from")
	public String from(){
		return "form";
	}
	@Autowired
	private IuserService userService;
	
	//登陆
	@RequestMapping("login")
	@ResponseBody
	public AjaxJson login(User user, HttpSession session) {
		boolean success = false;
		String msg = "账号或密码错误";
		
		List<User> list = userService.login(user);
		for (User user2 : list) {
			session.setAttribute("user", user2);
		}
		if (list.size() > 0) {
			success = true;
		}
		return new AjaxJson(success, msg);
	}
	
	// 跳转到主页面
	@RequestMapping("hrindex")
	public String index1(){
		
		return "index";
	}
	
	
	//注销
	@RequestMapping("loginsser")
	public String loginsser (HttpSession session){
		session.invalidate();
		return "login";
	}
	
	// 修改回显
	@RequestMapping("toupdate")
	public String toupdate(String id ,Map map){
			
		User user = userService.set(id);
		map.put("user", user);
		 
		return "loginuodate";
	}
	
	//修改
	@RequestMapping("update")
	@ResponseBody
	public Integer update(User user ){
		 Integer integer = userService.update(user);
		 return integer;
	}
	
}
