package com.yz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yz.dao.RoleMapper;
import com.yz.dao.RoleResMapper;
import com.yz.entity.Resources;
import com.yz.entity.Role;
import com.yz.entity.RoleRes;
import com.yz.entity.RoleResExample;
import com.yz.service.IroleService;
@Service 
public class RoleServiceimpl implements IroleService {
	
	@Autowired
	private RoleMapper rolemap;
	
	
	// 列表加分页
	@Override
	public Map<String, Object> list(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<Role> list = rolemap.selectByExample(null);
		PageInfo<Role> pageInfo = new PageInfo<>(list);
		Map<String ,Object> map = new HashMap<>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", list);
		
		return map;
	}

	// 添加 修改
	@Override
	public Integer add_update(Role role) {

		int ret =0 ;
		if(role.getId()==null){
			role.setCreateDate(new Date());
			ret += rolemap.insertSelective(role);
		}else{
			role.setUpdateDate(new Date());
			ret+= rolemap.updateByPrimaryKeySelective(role);
		}
		
		return ret ;
	}
	@Autowired
	private RoleResMapper roleresmap;
	
	//保存权限到 关系表
	@Override
	public Integer addauth(Integer roleId, String resIds) {
		int ret = 0;
//		String id = resIds.substring(1);
//		 //查询角色id为roleId的中间表中的数据,将这些数据删除，将新传来的数据添加
      /*RoleResExample example = new RoleResExample();
			example.createCriteria().andRoleIdEqualTo(new Long(roleId));
		
      List<RoleRes> roleReslist = roleresmap.selectByExample(example);//查出该角色有的权限的集合
*/      
		
      ret += roleresmap.delByRoleId(roleId);
      
      //分割字符串
		String[] rids =  resIds.split(",");
		if(rids != null && rids.length > 0 ){
			for (String reid : rids) {
				if(reid.length() > 0 ){
					RoleRes rr = new RoleRes();
					rr.setResourceId(new Long(reid));
					rr.setRoleId(new Long(roleId));
					ret +=roleresmap.insert(rr);
				}
			}
		}
		//delete from t_sys_role_res where role_id = ?
		return ret;
		
	}
	
	
	// 角色分配 用户的树
	@Override
	public List<Role> findResByRoleID(Integer userId) {
		return rolemap.findroleByRoleID(userId);
	}
	
	//删除
	@Override
	public Integer del(String ids) {
		int ret = 0;
		String[] split = ids.split(",");
		
		//先删除中间表
		for (int i = 0; i < split.length; i++) {
			ret += roleresmap.delByRoleId(Integer.parseInt(split[i]));
		}
		//在删除本表
		for (int i = 0; i < split.length; i++) {
			ret=rolemap.deleteByPrimaryKey(new Long(split[i]));
		}
		return ret;
	}
}
