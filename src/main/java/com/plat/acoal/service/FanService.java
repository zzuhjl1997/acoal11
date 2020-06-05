package com.plat.acoal.service;

import com.plat.acoal.model.DevInfo;

import java.util.List;
import java.util.Map;

public interface FanService {
    List<DevInfo> selectFanListByCondition(Map<String, String> condition);
}
