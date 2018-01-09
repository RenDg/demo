package com.yz.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yz.entity.Resources;
import com.yz.entity.Role;
import com.yz.service.IroleService;
import com.yz.vo.ZtreeNode;

@Controller
@RequestMapping("role")
public class RoleController {
	
	
	@RequestMapping("index")
	public String index (){
		
		return "role/index";
	}
	@Autowired
	private IroleService roleService;
	
	//�б�ӷ�ҳ
	@RequestMapping("list")
	@ResponseBody
	public Map<String ,Object> list(Integer page ,Integer rows){
		
		return roleService.list(page ,rows);
		
	}
	//�����ת
	@RequestMapping("toadd")
	public String toadd(){
		
		return "role/add";
	}
	
	// ����޸�
	@RequestMapping("add")
	@ResponseBody
	public Integer add_update(Role role ){
		
		return roleService.add_update(role);
	}
	
	//��Ȩ��ת
	@RequestMapping("v_auth")
	public String v_auth(Integer roleId, Map map){
		 map.put("roleId",roleId);
		return "role/auth";
	}
	
	//����Ȩ��
	@RequestMapping("o_auth")
	@ResponseBody
	public Integer o_auth(Integer roleId , String resIds ){
		return roleService.addauth(roleId,resIds);
	}
	
	
	//�û���Ȩ��
	@RequestMapping("v_ztree")
	@ResponseBody
	public List<ZtreeNode> ztree(Integer userId){
		List<ZtreeNode> ztrnodelist =  new ArrayList<>();
		//  ��ѯ��ɫ��ID 
		List<Role> roleist =roleService.findResByRoleID(userId);
		ZtreeNode ztreenode=null;
		if(roleist!=null&&!roleist.isEmpty()){
			for (Role role : roleist) {
				ztreenode = new ZtreeNode();
				ztreenode.setId(role.getId());
				ztreenode.setName(role.getRoleName());
				if (role.getUserId() != null) {
					 ztreenode.setChecked(true);
		            }
				ztrnodelist.add(ztreenode);
			}
		}
		return ztrnodelist;
	}
	
	//ɾ��
	@RequestMapping("del")
	@ResponseBody
	public Integer del (String  ids ){
		return 	roleService.del(ids);
	}
}
