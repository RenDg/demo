package com.yz.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yz.dao.UserMapper;
import com.yz.dao.UserRoleMapper;
import com.yz.entity.RoleRes;
import com.yz.entity.RoleResExample;
import com.yz.entity.User;
import com.yz.entity.UserExample;
import com.yz.entity.UserRoleExample;
import com.yz.entity.UserExample.Criteria;
import com.yz.entity.UserRole;
import com.yz.service.IuserService;
@Service
public class UserServiceimpl implements IuserService {
	
	@Autowired
	private UserMapper usermap;
	@Autowired
	private UserRoleMapper userrolemap;
	//��½�ļ���
	@Override
	public List<User> login(User user) {
		
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		criteria.andNameEqualTo(user.getUsername());
		criteria.andPwdEqualTo(user.getPwd());
		List<User> list = usermap.selectByExample(example);
		
		return list;
	}
	// ��¼�޸ĵĲ�ѯ
	@Override
	public User set(String id) {
		User user = usermap.selectByPrimaryKey(new Long(id));
		
		return user;
	}
	
	
	
	//�޸� ���
	@Override
	public Integer update(User user,Long[] roleIds) {
		int ret = 0; 
		//����˼·��
		//1.�ȱ����û�
		//2.�����û���ID �� ��ɫID ��user_role��
		//2.1 ��ȡ��������õ�ID , ʹ�� selectKey ��ǩ
		if(user.getId()==null){
			user.setCreateDate(new Date());
			ret +=usermap.insert(user);
		}else{
			//�޸�
			//1.�޸��û�
			//2.�޸Ĺ�ϵ�� user_role
			//2.1 ��ɾ����ǰ�û����еĽ�ɫ
			//2.2 ���������
			user.setUpdateDate(new Date());
			ret+= usermap.updateByPrimaryKeySelective(user);
			UserRoleExample example = new UserRoleExample();
			com.yz.entity.UserRoleExample.Criteria criteria = example.createCriteria();
			criteria.andUserIdEqualTo(user.getId());
			//2.1 ��ɾ����ǰ�û����еĽ�ɫ
			ret += userrolemap.deleteByExample(example);
		}
		
		//������ݵ� user_role��ϵ����
		if(roleIds != null) {
			for (long roleId : roleIds) {
				UserRole ur = new UserRole();
				ur.setUserId(user.getId());
				ur.setRoleId(roleId);
				ret += userrolemap.insert(ur);
			}
		}
		return ret ;
	}
	
	// �б�
	@Override
	public Map<String, Object> list(Integer page, Integer rows, Integer deptId, String name, 
			String firstDate,
			String endDate) {
		PageHelper.startPage(page, rows);
		UserExample example = new UserExample();
		Criteria criteria = example.createCriteria();
		
		if(deptId!=null&&deptId!=0){
			
			criteria.andDeptIdEqualTo(new Long(deptId));
		}
		Date minDate = null;
		Date maxDate = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		if (firstDate != null && endDate != null && firstDate != "" && endDate != "") {
			try {
				minDate = dateFormat.parse(firstDate);
				maxDate = dateFormat.parse(endDate);
				
				/*Calendar c = Calendar.getInstance();
				c.setTime(maxDate);
				c.add(Calendar.DAY_OF_MONTH, 1);
				maxDate = c.getTime();*/
				
				
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
		if(minDate!=null){
			criteria.andCreateDateGreaterThanOrEqualTo(minDate);
		}
		
		if(maxDate!=null){
			criteria.andCreateDateLessThanOrEqualTo(maxDate);
		}
		
		if(name!=null &&name!=""){
			criteria.andUsernameLike("%"+name+"%");
		}
		List<User> list = usermap.selectByExample(example);
		PageInfo<User> pageInfo = new PageInfo<>(list);
		Map<String ,Object> map = new HashMap<>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", list);
		return map;
	}
	//ɾ��
	@Override
	public Integer del(String ids) {
		int ret = 0;
		String[] split = ids.split(",");
		
		//��ɾ���м��
		for (int i = 0; i < split.length; i++) {
			ret += userrolemap.delByUserId(Integer.parseInt(split[i]));
		}
		//��ɾ������
		for (int i = 0; i < split.length; i++) {
			ret=usermap.deleteByPrimaryKey(new Long(split[i]));
		}
		return ret;
	}
	
	@Override
	public Integer update(User user) {
		return  usermap.updateByPrimaryKeySelective(user);
	}
	
	
	// �����Ȩ���м��
	//����Ȩ�޵� ��ϵ��
	@Override
	public Integer addauth(Integer userid, String resIds) {
		int ret = 0;
		ret += userrolemap.delByUserId(userid);
		//�ָ��ַ���
		String[] rids =  resIds.split(",");
		if(rids != null && rids.length > 0 ){
			for (String reid : rids) {
				if(reid.length() > 0 ){
					UserRole rr = new UserRole();
					rr.setUserId(new Long(userid));
					rr.setRoleId(new Long(reid));
					ret +=userrolemap.insert(rr);
				}
			}
		}
		//delete from t_sys_role_res where role_id = ?
		return ret;
		
	}

}
