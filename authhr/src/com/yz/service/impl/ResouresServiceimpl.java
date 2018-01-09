package com.yz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yz.dao.ResourcesMapper;
import com.yz.entity.Resources;
import com.yz.entity.ResourcesExample;
import com.yz.entity.ResourcesExample.Criteria;
import com.yz.service.IresouresService;
@Service
public class ResouresServiceimpl implements IresouresService{
	@Autowired
	private ResourcesMapper resouresmap;
	
	@Override
	public Map<String, Object> list() {
		
		List<Resources> list = resouresmap.selectByExample(null);
		
		Map<String, Object> map  = new HashMap<>();
		map.put("total", "");
		map.put("rows", list);
		
		return map;
	}
	
	//上级资源
	@Override
	public List<Resources> findall() {
		List<Resources> list = resouresmap.selectByExample(null);
		
		return list;
	}
	
	//添加修改
	@Override
	public Integer add_update(Resources resoures) {
		
		int ret = 0;
		if(resoures.getId()==null){
			resoures.setCreateDate(new Date());
			ret += resouresmap.insertSelective(resoures);
		}else{
			resoures.setUpdateDate(new Date());
			ret += resouresmap.updateByPrimaryKeySelective(resoures);
		}
		
		return ret;
	}
	
	//修改回显
	@Override
	public Resources setid(String id) {
		Resources resources = resouresmap.selectByPrimaryKey(new Long(id));
		return resources;
	}
	//删除
	@Override
	public Integer del(Long id) {
		return	resouresmap.deleteByPrimaryKey(id);
	}
	
	
	// 回显选中的节点
	@Override
	public List<Resources> findResByRoleID(Integer roleid) {
		return resouresmap.findResByRoleID(roleid);
	}
	
	@Override
	public List<Resources> findalllist(Resources res_) {
		ResourcesExample example = new ResourcesExample();
		Criteria criteria = example.createCriteria();
		
		criteria.andResTypeEqualTo("0");
		List<Resources> list = resouresmap.selectByExample(example);
		return list;
	}

	
}
