package com.plat.acoal.service.impl;

import com.plat.acoal.dao.FanMapper;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.service.FanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class FanServiceImpl implements FanService {

    @Autowired
    private FanMapper fm;

    @Override
    public List<DevInfo> selectFanListByCondition(Map<String, String> condition) {
        return fm.selectFanListByCondition(condition);
    }
}
