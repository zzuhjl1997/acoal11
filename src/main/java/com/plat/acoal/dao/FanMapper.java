package com.plat.acoal.dao;

import com.plat.acoal.entity.Fan;
import com.plat.acoal.model.DevInfo;

import java.util.List;
import java.util.Map;

public interface FanMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Fan record);

    int insertSelective(Fan record);

    Fan selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Fan record);

    int updateByPrimaryKey(Fan record);

    List<DevInfo> selectFanListByCondition(Map<String, String> condition);
}