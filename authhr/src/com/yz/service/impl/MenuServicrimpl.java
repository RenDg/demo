package com.yz.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yz.dao.MenuMapper;
import com.yz.entity.Menu;
import com.yz.service.ImenuService;
@Service
public class MenuServicrimpl implements ImenuService {
	
	@Autowired
	private MenuMapper menumap;
	
	@Override
	public List<Menu> findall() {
		List<Menu> list = menumap.selectByExample(null);
		return list;
	}

}
