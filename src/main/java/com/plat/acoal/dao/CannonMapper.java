package com.plat.acoal.dao;

import com.plat.acoal.entity.Cannon;
import com.plat.acoal.model.CannonInfo;
import com.plat.acoal.model.DevInfo;

import java.util.List;
import java.util.Map;

public interface CannonMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Cannon record);

    int insertSelective(Cannon record);

    Cannon selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Cannon record);

    int updateByPrimaryKey(Cannon record);

    List<DevInfo> selectCannonList(Map<String, String> condition);

    List<CannonInfo> selectNewCannonById(Map<String, String> condition);

    int selectCannonCount(Map<String, String> condition);
}