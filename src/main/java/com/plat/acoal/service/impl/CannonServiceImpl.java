package com.plat.acoal.service.impl;

import com.github.pagehelper.PageHelper;
import com.plat.acoal.dao.CannonMapper;
import com.plat.acoal.model.CannonInfo;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.service.CannonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
@Service
public class CannonServiceImpl implements CannonService {
    @Autowired
    private CannonMapper cam;
    @Override
    public List<DevInfo> selectCannonList(Map<String, String> condition,Integer currentPage,Integer pageSize) {
        if(currentPage != null){
            PageHelper.startPage(currentPage,1);
        }
        return cam.selectCannonList(condition);
    }

    @Override
    public List<CannonInfo> selectNewCannonById(Map<String,String> condition) {

        return cam.selectNewCannonById(condition);
    }

    @Override
    public int selectCannonCount(Map<String, String> condition) {
        return cam.selectCannonCount(condition);
    }
}
