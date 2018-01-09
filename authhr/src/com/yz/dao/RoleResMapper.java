package com.yz.dao;

import com.yz.entity.RoleRes;
import com.yz.entity.RoleResExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface RoleResMapper {
    int countByExample(RoleResExample example);

    int deleteByExample(RoleResExample example);

    int insert(RoleRes record);

    int insertSelective(RoleRes record);

    List<RoleRes> selectByExampleWithRowbounds(RoleResExample example, RowBounds rowBounds);

    List<RoleRes> selectByExample(RoleResExample example);

    int updateByExampleSelective(@Param("record") RoleRes record, @Param("example") RoleResExample example);

    int updateByExample(@Param("record") RoleRes record, @Param("example") RoleResExample example);
    
    int delByRoleId(Integer roleId);
}