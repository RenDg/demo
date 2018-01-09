package com.yz.service;

import java.util.List;
import java.util.Map;

import com.yz.entity.Dept;
import com.yz.entity.User;
import com.yz.vo.AjaxJson;

public interface IdeptService {
	
	Map<String, Object> list();
	
	
	boolean del(String ids);
	
	Dept findll(String id);
		
	List<Dept> findall();
	
	List<Dept> findDeptByRoleID(Integer deptId);
	
	List<Dept> findByPid(String ids);

	boolean findUserByDeptId(String ids);


	boolean add(Dept dept);


	boolean update(Dept dept);

}
