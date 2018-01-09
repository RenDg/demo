package com.yz.action;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class text {

	@RequestMapping("index.shtml")
	public String index(){
		System.out.println(11);
		return "index";
	}
	
}
