package com.yz.dao;

import com.yz.entity.UserRole;
import com.yz.entity.UserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UserRoleMapper {
    int countByExample(UserRoleExample example);

    int deleteByExample(UserRoleExample example);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    List<UserRole> selectByExampleWithRowbounds(UserRoleExample example, RowBounds rowBounds);

    List<UserRole> selectByExample(UserRoleExample example);

    int updateByExampleSelective(@Param("record") UserRole record, @Param("example") UserRoleExample example);

    int updateByExample(@Param("record") UserRole record, @Param("example") UserRoleExample example);
    
    
    int delByUserId(Integer userid);
}