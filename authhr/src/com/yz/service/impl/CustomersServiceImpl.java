package com.yz.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yz.dao.CustomersMapper;
import com.yz.entity.Customers;
import com.yz.service.IcustomersService;
import com.yz.vo.AjaxJson;
@Service
public class CustomersServiceImpl implements IcustomersService {
	@Autowired
	private CustomersMapper cusmap;
	@Override
	public Map<String, Object> list(Integer page, Integer rows) {
		
		PageHelper.startPage(page, rows);
		List<Customers> list = cusmap.selectByExample(null);
		PageInfo<Customers> pageInfo = new PageInfo<>(list);
		Map<String, Object> map =  new HashMap<>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", list);
		return map;
	}
	//Ìí¼Ó
	@Override
	public boolean add(Customers customers) {
		int i = cusmap.insertSelective(customers);
		if(i>0){
			return true;
		}
		return false;
	}
	//ÐÞ¸Ä
	@Override
	public boolean update(Customers customers) {
		int i = cusmap.updateByPrimaryKeySelective(customers);
		if(i>0){
			return true;
		}
		return false;
	}
	@Override
	public Customers set(String id) {
		Customers customers = cusmap.selectByPrimaryKey(Integer.parseInt(id));
		return customers;
	}
	@Override
	public boolean del(String ids) {
		int ret = 0;
		String[] split = ids.split(",");
		for (int i = 0; i < split.length; i++) {
			ret=cusmap.deleteByPrimaryKey(Integer.parseInt(split[i]));
		}
		
		if(ret>0){
			return true;
		}
		
		return false;
	}
	
	

}
