package com.plat.acoal.service;

import com.plat.acoal.model.CannonInfo;
import com.plat.acoal.model.DevInfo;

import java.util.List;
import java.util.Map;

public interface CannonService {
    List<DevInfo> selectCannonList(Map<String,String> condition);

    List<CannonInfo> selectNewCannonById(Map<String, String> condition);
}
