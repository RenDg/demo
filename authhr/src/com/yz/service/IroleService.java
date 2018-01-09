package com.yz.service;

import java.util.List;
import java.util.Map;

import com.yz.entity.Resources;
import com.yz.entity.Role;

public interface IroleService {

	Map<String, Object> list(Integer page, Integer rows);

	Integer add_update(Role role);

	Integer addauth(Integer roleId, String resIds);
	
	List<Role> findResByRoleID(Integer userId);

	Integer del(String ids);
	
	

}
