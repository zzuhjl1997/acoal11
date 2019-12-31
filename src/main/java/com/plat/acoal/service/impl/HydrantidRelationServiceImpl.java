package com.plat.acoal.service.impl;

import com.plat.acoal.dao.HydrantidRelationMapper;
import com.plat.acoal.entity.HydrantidRelation;
import com.plat.acoal.service.HydrantidRelationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class HydrantidRelationServiceImpl  implements HydrantidRelationService {
    @Autowired
    public HydrantidRelationMapper hydrantidRelationMapper;

    @Override
    public HydrantidRelation selectHydByHId(Integer id) {
        return hydrantidRelationMapper.selectByHid(id);
    }
}
