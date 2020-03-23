package com.plat.acoal.service;

import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.DustModel;

import java.util.List;

public interface DustService {
    /**
     * 查询最新粉尘浓度
     * @param
     * @return
     */
    List<DustModel> selectNewInfoById(DustModel dustModel);


    /**
     * 查询一天的数据
     * @return
     */
    List<DustModel> selectInfoByHour (DustModel dustModel);

    List<DevInfo> selectDustList(DevInfo devInfo,Integer currentPage,Integer pageSize);
}
