package com.yz.dao;

import com.yz.entity.GoodsAttributeItem;
import com.yz.entity.GoodsAttributeItemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface GoodsAttributeItemMapper {
    int countByExample(GoodsAttributeItemExample example);

    int deleteByExample(GoodsAttributeItemExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(GoodsAttributeItem record);

    int insertSelective(GoodsAttributeItem record);

    List<GoodsAttributeItem> selectByExampleWithRowbounds(GoodsAttributeItemExample example, RowBounds rowBounds);

    List<GoodsAttributeItem> selectByExample(GoodsAttributeItemExample example);

    GoodsAttributeItem selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") GoodsAttributeItem record, @Param("example") GoodsAttributeItemExample example);

    int updateByExample(@Param("record") GoodsAttributeItem record, @Param("example") GoodsAttributeItemExample example);

    int updateByPrimaryKeySelective(GoodsAttributeItem record);

    int updateByPrimaryKey(GoodsAttributeItem record);
}