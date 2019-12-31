package com.plat.acoal.dao;

import com.plat.acoal.entity.Flow;
import com.plat.acoal.model.PressureFlowModel;

import java.util.List;

public interface FlowMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Flow record);

    int insertSelective(Flow record);

    Flow selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Flow record);

    int updateByPrimaryKey(Flow record);

    List<PressureFlowModel> selectNewFById(PressureFlowModel pressFlowModelModel);

    List<PressureFlowModel> selectFByHour(PressureFlowModel pressureFlowModel);
}