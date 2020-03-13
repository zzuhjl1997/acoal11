package com.plat.acoal.dao;

import com.plat.acoal.entity.Pressure;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.PressureFlowModel;

import java.util.List;

public interface PressureMapper {
//    int deleteByPrimaryKey(Long id);
//
//    int insert(Pressure record);
//
//    int insertSelective(Pressure record);

    Pressure selectByPrimaryKey(Long id);

//    int updateByPrimaryKeySelective(Pressure record);
//
//    int updateByPrimaryKey(Pressure record);

    List<PressureFlowModel> selectNewPById(PressureFlowModel pressureFlowModel);

    List<PressureFlowModel> selectPByHour(PressureFlowModel pressureFlowModel);

    List<DevInfo> selectPList(DevInfo devInfo);
}