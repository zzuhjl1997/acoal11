package com.plat.acoal.dao;
import com.plat.acoal.entity.DevActive;
public interface DevActiveMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(DevActive record);
    int insertSelective(DevActive record);
    DevActive selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(DevActive record);
    int updateByPrimaryKey(DevActive record);
}