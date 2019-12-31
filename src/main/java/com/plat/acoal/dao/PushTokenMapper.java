package com.plat.acoal.dao;

import com.plat.acoal.entity.PushToken;

public interface PushTokenMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(PushToken record);

    int insertSelective(PushToken record);

    PushToken selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(PushToken record);

    int updateByPrimaryKey(PushToken record);
}