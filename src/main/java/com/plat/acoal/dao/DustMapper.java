package com.plat.acoal.dao;

import com.plat.acoal.entity.Dust;
import com.plat.acoal.model.DustModel;

import java.util.List;

public interface DustMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Dust record);

    int insertSelective(Dust record);

    Dust selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Dust record);

    int updateByPrimaryKey(Dust record);

    List<DustModel> selectNewInfoById(DustModel dustModel);

    List<DustModel> selectInfoByHour(DustModel dustModel);

    List<Dust> selectDustByRegionId(Integer id);
}