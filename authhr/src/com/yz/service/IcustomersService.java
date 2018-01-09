package com.yz.service;

import java.util.Map;

import com.yz.entity.Customers;
import com.yz.vo.AjaxJson;

public interface IcustomersService {

	Map<String, Object> list(Integer page, Integer rows);

	boolean add(Customers customers);

	boolean update(Customers customers);

	Customers set(String id);

	boolean del(String ids);


}
