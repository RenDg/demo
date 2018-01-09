package com.yz.service;

import java.util.List;
import java.util.Map;

import com.yz.entity.User;

public interface IuserService {
	//µÇÂ¼
	List<User> login(User user);
	
	User set(String id);
	
	Integer update(User user, Long[] roleIds);
	
	Map<String, Object> list(Integer page, Integer rows,Integer deptId, String name, String firstDate, String endDate);
	
	Integer del(String ids);
	
	Integer update(User user);

	Integer addauth(Integer userid, String resIds);
	
		


}
