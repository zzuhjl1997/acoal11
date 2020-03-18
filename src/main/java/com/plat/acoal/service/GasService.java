package com.plat.acoal.service;

import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.GasModel;

import java.util.List;

public interface GasService {
    /**
     * 查询当前最新Ch4 Co
     * @param
     * @return
     */
    List<GasModel> selectNewCh4ById(GasModel gasModel);
    List<GasModel> selectNewCoById(GasModel gasModel);


    /**
     * 查询一天的数据
     * @return
     */
    List<GasModel> selectCh4ByHour (GasModel gasModel);
    List<GasModel> selectCoByHour (GasModel gasModel);

    /**
     * 查询甲烷列表
     * @param devInfo
     * @return
     */
    List<DevInfo> selectCh4List(DevInfo devInfo,Integer currentPage,Integer ageSize);

    /**
     * 查询一氧化碳列表
     * @param devInfo
     * @return
     */
    List<DevInfo> selectCoList(DevInfo devInfo);
}
