package com.plat.acoal.service.impl;

import com.plat.acoal.dao.GasMapper;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.GasModel;
import com.plat.acoal.service.GasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class GasServiceImpl implements GasService {
    @Autowired
    public GasMapper gasMapper;

    @Override
    public List<GasModel> selectNewCh4ById(GasModel gasModel) {
        return gasMapper.selectNewCh4ById(gasModel);
    }

    @Override
    public List<GasModel> selectNewCoById(GasModel gasModel) {
        return gasMapper.selectNewCoById(gasModel);
    }

    @Override
    public List<GasModel> selectCh4ByHour(GasModel gasModel) {
        return gasMapper.selectCh4ByHour(gasModel);
    }

    @Override
    public List<GasModel> selectCoByHour(GasModel gasModel) {
        return gasMapper.selectCoByHour(gasModel);
    }


    @Override
    public List<DevInfo> selectCh4List(DevInfo devInfo) {
        return gasMapper.selectCh4List(devInfo);
    }

    @Override
    public List<DevInfo> selectCoList(DevInfo devInfo) {
        return gasMapper.selectCoList(devInfo);
    }
}
