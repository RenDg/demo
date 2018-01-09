package com.yz.service;

import java.util.List;
import java.util.Map;

import com.yz.entity.Resources;

public interface IresouresService {

	Map<String, Object> list();
	
	List<Resources> findall();

	Integer add_update(Resources resoures);

	Resources setid(String id);

	Integer del(Long id);
		
	public  List<Resources> findResByRoleID(Integer roleid);
	
	List<Resources> findalllist(Resources res_);
}
