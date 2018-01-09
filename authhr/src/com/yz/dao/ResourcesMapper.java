package com.yz.dao;

import com.yz.entity.Resources;
import com.yz.entity.ResourcesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface ResourcesMapper {
    int countByExample(ResourcesExample example);

    int deleteByExample(ResourcesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(Resources record);

    int insertSelective(Resources record);

    List<Resources> selectByExampleWithRowbounds(ResourcesExample example, RowBounds rowBounds);

    List<Resources> selectByExample(ResourcesExample example);

    Resources selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByExample(@Param("record") Resources record, @Param("example") ResourcesExample example);

    int updateByPrimaryKeySelective(Resources record);

    int updateByPrimaryKey(Resources record);
    
    public  List<Resources> findResByRoleID(Integer roleid);
    
}