package com.yz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yz.dao.DeptMapper;
import com.yz.entity.Dept;
import com.yz.entity.DeptExample;
import com.yz.entity.DeptExample.Criteria;
import com.yz.entity.User;
import com.yz.service.IdeptService;
@Service
public class DeptServicrimpl implements IdeptService{
	@Autowired
	private DeptMapper deptmap;
	
	@Override
	public Map<String, Object> list() {
		List<Dept> list = deptmap.selectByExample(null);
		Map<String, Object> map  = new HashMap<>();
		map.put("total","");
		map.put("rows", list);
		return map;
	}
	
	//添加 修改
	/*@Override
	public Integer add_update(Dept dept) {
		int ret=0;
		if(dept.getId()==null){
			dept.setCreateDate(new Date());
			ret +=deptmap.insertSelective(dept);
		}else{
			if(dept.getId().equals(dept.getPid())){
			 }
			dept.setUpdateDate(new Date());
			ret+=deptmap.updateByPrimaryKeySelective(dept);
		}
		return ret;
	}*/
	
	//删除
	@Override
	public boolean del(String ids) {
		int ret = 0;
		String[] split = ids.split(",");
		for (int i = 0; i < split.length; i++) {
			ret=deptmap.deleteByPrimaryKey(new Long(split[i]));
		}
		
		if(ret>0){
			return true;
		}
		
		return false;
	}
	
	//修改回显
	@Override
	public Dept findll(String id) {
		Dept dept = deptmap.selectByPrimaryKey(new Long(id));
		return dept;
	}
	
	//ztrr 树  上级资源
	@Override
	public List<Dept> findall() {
		return	deptmap.selectByExample(null);
	}

	@Override
	public List<Dept> findDeptByRoleID(Integer deptId) {
		List<Dept> list = deptmap.findDeptByRoleID(deptId);
		return list;
	}

	@Override
	public List<Dept> findByPid(String ids) {
		DeptExample example = new DeptExample();
		Criteria criteria = example.createCriteria();
		criteria.andPidEqualTo(new Long(ids));
		return deptmap.selectByExample(example);
	}

	@Override
	public boolean findUserByDeptId(String ids) {
		List<Dept> list = deptmap.findUserByDeptId(Integer.parseInt(ids));
		if(list.size()>0){
			return true ;
		}
		return  false;
	}

	@Override
	public boolean add(Dept dept) {
		int i = deptmap.insertSelective(dept);
		System.out.println(i);
		if(i>0){
			return true ;
		}
		return false;
	}

	@Override
	public boolean update(Dept dept) {
		int i =deptmap.updateByPrimaryKeySelective(dept);
		if(i>0){
			return true ;
		}
		return false;
	}

}
