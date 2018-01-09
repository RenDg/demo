package com.yz.service.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yz.dao.RoleMapper;
import com.yz.dao.RoleResMapper;
import com.yz.entity.Resources;
import com.yz.entity.Role;
import com.yz.entity.RoleRes;
import com.yz.entity.RoleResExample;
import com.yz.service.IroleService;
@Service 
public class RoleServiceimpl implements IroleService {
	
	@Autowired
	private RoleMapper rolemap;
	
	
	// �б�ӷ�ҳ
	@Override
	public Map<String, Object> list(Integer page, Integer rows) {
		PageHelper.startPage(page, rows);
		List<Role> list = rolemap.selectByExample(null);
		PageInfo<Role> pageInfo = new PageInfo<>(list);
		Map<String ,Object> map = new HashMap<>();
		map.put("total", pageInfo.getTotal());
		map.put("rows", list);
		
		return map;
	}

	// ��� �޸�
	@Override
	public Integer add_update(Role role) {

		int ret =0 ;
		if(role.getId()==null){
			role.setCreateDate(new Date());
			ret += rolemap.insertSelective(role);
		}else{
			role.setUpdateDate(new Date());
			ret+= rolemap.updateByPrimaryKeySelective(role);
		}
		
		return ret ;
	}
	@Autowired
	private RoleResMapper roleresmap;
	
	//����Ȩ�޵� ��ϵ��
	@Override
	public Integer addauth(Integer roleId, String resIds) {
		int ret = 0;
//		String id = resIds.substring(1);
//		 //��ѯ��ɫidΪroleId���м���е�����,����Щ����ɾ�������´������������
      /*RoleResExample example = new RoleResExample();
			example.createCriteria().andRoleIdEqualTo(new Long(roleId));
		
      List<RoleRes> roleReslist = roleresmap.selectByExample(example);//����ý�ɫ�е�Ȩ�޵ļ���
*/      
		
      ret += roleresmap.delByRoleId(roleId);
      
      //�ָ��ַ���
		String[] rids =  resIds.split(",");
		if(rids != null && rids.length > 0 ){
			for (String reid : rids) {
				if(reid.length() > 0 ){
					RoleRes rr = new RoleRes();
					rr.setResourceId(new Long(reid));
					rr.setRoleId(new Long(roleId));
					ret +=roleresmap.insert(rr);
				}
			}
		}
		//delete from t_sys_role_res where role_id = ?
		return ret;
		
	}
	
	
	// ��ɫ���� �û�����
	@Override
	public List<Role> findResByRoleID(Integer userId) {
		return rolemap.findroleByRoleID(userId);
	}
	
	//ɾ��
	@Override
	public Integer del(String ids) {
		int ret = 0;
		String[] split = ids.split(",");
		
		//��ɾ���м��
		for (int i = 0; i < split.length; i++) {
			ret += roleresmap.delByRoleId(Integer.parseInt(split[i]));
		}
		//��ɾ������
		for (int i = 0; i < split.length; i++) {
			ret=rolemap.deleteByPrimaryKey(new Long(split[i]));
		}
		return ret;
	}
}
