package com.plat.acoal.dao;

import com.plat.acoal.entity.PressureFlow;
import com.plat.acoal.model.PressureFlowModel;

import java.util.List;

public interface PressureFlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(PressureFlow record);

    int insertSelective(PressureFlow record);

    PressureFlow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(PressureFlow record);

    int updateByPrimaryKey(PressureFlow record);


    List<PressureFlowModel> selectNewInfoById(PressureFlowModel pressureFlowModel);

    List<PressureFlowModel> selectInfoByHour(PressureFlowModel pressureFlowModel);
}