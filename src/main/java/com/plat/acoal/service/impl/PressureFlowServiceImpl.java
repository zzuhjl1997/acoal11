package com.plat.acoal.service.impl;

import com.plat.acoal.dao.FlowMapper;
import com.plat.acoal.dao.PressureFlowMapper;
import com.plat.acoal.dao.PressureMapper;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.PressureFlowModel;
import com.plat.acoal.service.PressureFlowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class PressureFlowServiceImpl implements PressureFlowService {
    @Autowired
    public PressureMapper pressureMapper;
    @Autowired
    public FlowMapper flowMapper;


    @Override
    public List<PressureFlowModel> selectNewPById(PressureFlowModel pressFlowModelModel) {
        return pressureMapper.selectNewPById(pressFlowModelModel);
    }

    @Override
    public List<PressureFlowModel> selectPByHour(PressureFlowModel pressureFlowModel) {
        return pressureMapper.selectPByHour(pressureFlowModel);
    }

    @Override
    public List<PressureFlowModel> selectFByHour(PressureFlowModel pressureFlowModel) {
        return flowMapper.selectFByHour(pressureFlowModel);
    }

    @Override
    public List<PressureFlowModel> selectNewFById(PressureFlowModel pressFlowModelModel) {
        return flowMapper.selectNewFById(pressFlowModelModel);
    }

    @Override
    public List<DevInfo> selectPList(DevInfo devInfo) {

        return pressureMapper.selectPList(devInfo);
    }

    @Override
    public List<DevInfo> selectFList(DevInfo devInfo) {
        return flowMapper.selectFList(devInfo);
    }


}
