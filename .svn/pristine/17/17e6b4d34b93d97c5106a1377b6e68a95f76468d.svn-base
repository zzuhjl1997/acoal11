package com.plat.acoal.service.impl;

import com.plat.acoal.dao.DeptMapper;
import com.plat.acoal.dao.DustMapper;
import com.plat.acoal.model.DustModel;
import com.plat.acoal.service.DustService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class DustServiceImpl implements DustService {
    @Autowired
    public DustMapper dustMapper;
    @Override
    public List<DustModel> selectNewInfoById(DustModel dustModel) {
        return dustMapper.selectNewInfoById(dustModel);
    }

    @Override
    public List<DustModel> selectInfoByHour(DustModel dustModel) {
        return dustMapper.selectInfoByHour(dustModel);
    }
}
