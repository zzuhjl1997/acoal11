package com.plat.acoal.service.impl;

import com.plat.acoal.dao.TemperatureMapper;
import com.plat.acoal.entity.Temperature;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.TemperatureInfo;
import com.plat.acoal.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TemperatureServiceImpl implements TemperatureService {
    @Autowired
    public TemperatureMapper temperatureMapper;
    @Override
    public List<TemperatureInfo> selectNewFtById(TemperatureInfo temperatureInfo) {
        return temperatureMapper.selectNewFtById(temperatureInfo);
    }


    @Override
    public List<Temperature> selectFtByHour(TemperatureInfo temperatureInfo) {
        return temperatureMapper.selectFtByHour(temperatureInfo);
    }

    @Override
    public List<DevInfo> selectFtList(DevInfo devInfo) {
        return temperatureMapper.selectFtList(devInfo);
    }

}
