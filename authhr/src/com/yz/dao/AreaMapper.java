package com.yz.dao;

import com.yz.entity.Area;
import com.yz.entity.AreaExample;
import java.math.BigDecimal;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface AreaMapper {
    int countByExample(AreaExample example);

    int deleteByExample(AreaExample example);

    int deleteByPrimaryKey(BigDecimal id);

    int insert(Area record);

    int insertSelective(Area record);

    List<Area> selectByExampleWithRowbounds(AreaExample example, RowBounds rowBounds);

    List<Area> selectByExample(AreaExample example);

    Area selectByPrimaryKey(BigDecimal id);

    int updateByExampleSelective(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByExample(@Param("record") Area record, @Param("example") AreaExample example);

    int updateByPrimaryKeySelective(Area record);

    int updateByPrimaryKey(Area record);
}