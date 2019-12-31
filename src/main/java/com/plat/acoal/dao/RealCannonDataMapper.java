package com.plat.acoal.dao;

import com.plat.acoal.entity.RealCannonData;

import java.util.List;

public interface RealCannonDataMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(RealCannonData record);

    int insertSelective(RealCannonData record);

    RealCannonData selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(RealCannonData record);

    int updateByPrimaryKey(RealCannonData record);

    List<RealCannonData> selectByPrimaryDid(RealCannonData realCannonData);
}