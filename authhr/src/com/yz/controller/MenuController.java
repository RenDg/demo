package com.yz.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Menu;
import com.yz.service.ImenuService;
import com.yz.vo.ZtreeNode;

@Controller
@RequestMapping("menu")
public class MenuController {

	@Autowired
	private ImenuService menuService;
	
	
	@RequestMapping("/v_tree")
	@ResponseBody
	public List<ZtreeNode> aree(){
		List<ZtreeNode>  ztreenodelist = new ArrayList<>();
		List<Menu> menulist = menuService.findall();
		ZtreeNode ztreenode=null;
		if(menulist!=null&&!menulist.isEmpty()){
			for (Menu menu : menulist) {
				ztreenode = new ZtreeNode();
				ztreenode.setId(new Long(menu.getId()));
				ztreenode.setPid(new Long(menu.getParentId()));
				ztreenode.setName(menu.getName());
				ztreenode.setUrl(menu.getHref());
				ztreenode.setIcon(menu.getIcon());
				ztreenodelist.add(ztreenode);
			}
		}
		return ztreenodelist;
	}
}
