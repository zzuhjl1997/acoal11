package com.plat.acoal.service.impl;

import com.plat.acoal.dao.RegionMapper;
import com.plat.acoal.entity.Region;
import com.plat.acoal.model.RegionModel;
import com.plat.acoal.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class RegionServiceImpl implements RegionService {
    @Autowired
    private RegionMapper rm;


    @Override
    public List<RegionModel> selectRegionByCus(Integer customerid) {

        return rm.selectRegionByCustomerId(customerid);
    }
    @Override
    public List<RegionModel> selectRegionModelByCondition(Map<String, String> condition) {
        return rm.selectRegionModelByCondition(condition);
    }
}
