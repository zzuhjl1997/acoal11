package com.plat.acoal.dao;

import com.plat.acoal.entity.Region;
import com.plat.acoal.model.RegionModel;

import java.util.List;

public interface RegionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Region record);

    int insertSelective(Region record);

    Region selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Region record);

    int updateByPrimaryKey(Region record);

    List<RegionModel> selectRegionByCustomerId(Integer icustomerid);
}