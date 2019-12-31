package com.plat.acoal.dao;

import com.plat.acoal.entity.HydrantidRelation;

public interface HydrantidRelationMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(HydrantidRelation record);

    int insertSelective(HydrantidRelation record);

    HydrantidRelation selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(HydrantidRelation record);

    int updateByPrimaryKey(HydrantidRelation record);

    HydrantidRelation selectByHid(Integer id);
}