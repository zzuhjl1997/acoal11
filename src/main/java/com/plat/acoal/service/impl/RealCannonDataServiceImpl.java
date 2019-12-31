package com.plat.acoal.service.impl;

import com.plat.acoal.dao.RealCannonDataMapper;
import com.plat.acoal.entity.RealCannonData;
import com.plat.acoal.service.RealCannonDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RealCannonDataServiceImpl implements RealCannonDataService {
    @Autowired
    private RealCannonDataMapper realCannonDataMapper;
    @Override
    public List<RealCannonData> selectNowRealCannon(RealCannonData realCannonData) {
        return realCannonDataMapper.selectByPrimaryDid(realCannonData);
    }
}
