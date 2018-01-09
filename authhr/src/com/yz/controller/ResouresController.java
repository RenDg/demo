package com.yz.controller;
/**
 * 资源表
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Menu;
import com.yz.entity.Resources;
import com.yz.service.IresouresService;
import com.yz.vo.Node;
import com.yz.vo.ZtreeNode;

@Controller
@RequestMapping("/resoures")
public class ResouresController {

	@RequestMapping("index")
	public String index (){
		return "resoures/index";
	}
	
	@Autowired
	private IresouresService resservice;
	
	//菜单左边ztree树
	@RequestMapping("/v_tree")
	@ResponseBody
	public List<ZtreeNode> aree(){
		List<ZtreeNode>  ztreenodelist = new ArrayList<>();
		Resources res_ = new Resources();
		//res_.setResType("0");
		List<Resources> reslist = resservice.findalllist(res_);
		ZtreeNode ztreenode=null;
		if(reslist!=null&&!reslist.isEmpty()){
			for (Resources res : reslist) {
				ztreenode = new ZtreeNode();
				ztreenode.setId(res.getId());
				ztreenode.setPid(res.getPid());
				ztreenode.setName(res.getResName());
				ztreenode.setUrl(res.getUrl());
				ztreenodelist.add(ztreenode);
			}
		}
		return ztreenodelist;
	}
	
	//列表 加分页
	@RequestMapping("list")
	@ResponseBody
	public Map<String, Object> list1(/*Integer page ,Integer rows*/){
		Map<String, Object> map = resservice.list();
		return map;
	}
	
	//添加跳转
	@RequestMapping("toadd")
	public String toadd(){
		
		return "resoures/resadd";
	}
	//修改 添加
	@RequestMapping("add")
	@ResponseBody
	public Integer add_update(Resources resoures){
			
		return 	resservice.add_update(resoures);
		
	}
	
	
	//上级资源
	@RequestMapping("tree")
	@ResponseBody
	public List<Node> list (){
		List<Resources> reslist = resservice.findall();
		List<Node> nodelist = new ArrayList<Node>();
		 Node node = null;
		 for (Resources res : reslist) {
			 node = new Node();
			 node.setId(res.getId());
			 node.setName(res.getResName());
			 node.setparentId(res.getPid());
			 nodelist.add(node);
		}
		return nodelist;
	}
	
	//修改回显
	@RequestMapping("init")
	public String  init (String id ,Map map ){
		Resources resoures = resservice.setid(id);
		map.put("resoures", resoures);
		return "resoures/resadd";
	}
	//删除
	@RequestMapping("del")
	@ResponseBody
	public Integer del(Long id){
		
		return resservice.del(id);
	}
	
	
	//权限
	@RequestMapping("v_ztree")
	@ResponseBody
	public List<ZtreeNode> ztree(Integer roleId){
		List<ZtreeNode> ztrnodelist =  new ArrayList<>();
		//  查询角色的ID
		List<Resources> reslist =resservice.findResByRoleID(roleId);
		ZtreeNode ztreenode=null;
		if(reslist!=null&&!reslist.isEmpty()){
			for (Resources resources : reslist) {
				ztreenode = new ZtreeNode();
				ztreenode.setId(resources.getId());
				ztreenode.setPid(resources.getPid());
				ztreenode.setName(resources.getResName());
				 if (resources.getRoleid() != null) {
					 ztreenode.setChecked(true);
		            }
				 System.out.println(resources.getRoleid());
				ztrnodelist.add(ztreenode);
			}
		}
		return ztrnodelist;
	}
	
	
	
}
