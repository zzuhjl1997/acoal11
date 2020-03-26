package com.plat.acoal.service;

import com.plat.acoal.entity.Temperature;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.TemperatureInfo;

import java.util.List;
import java.util.Map;

public interface TemperatureService {
    /**
     * 查询当前最新温度
     * @param
     * @return
     */
    List<TemperatureInfo> selectNewFtById(TemperatureInfo temperatureInfo);

 /*   *//**
     * 查询一个小时的数据
     * @return
     *//*
    List<Temperature> selectFtByMinute (TemperatureInfo temperatureInfo);
*/
    /**
     * 查询一天的数据
     * @return
     */
    List<Temperature> selectFtByHour (TemperatureInfo temperatureInfo);

    /**
     * 查询温度监控列表
     * @param devInfo
     * @return
     */

    List<DevInfo> selectFtList(DevInfo devInfo,Integer currentPage,Integer pageSize);

    int selectFtCount(Map<String, String> condition);
}
