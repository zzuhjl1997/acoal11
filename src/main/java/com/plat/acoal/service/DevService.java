
package com.plat.acoal.service;

import com.github.pagehelper.PageInfo;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.model.*;
import com.plat.acoal.utils.JsonResult;

import java.util.List;
import java.util.Map;

public interface DevService {
    List<DevNameModel> selectNameByCondition(Map<String, String> condition);

    List<DevTypeModel> selectTypeByCondition(Map<String, String> condition);

    PageInfo<DevInfoModel> selectDevInfoModelByCondtition(Integer currentPage, Integer pageSize, Map<String, String> condition);

    PageInfo<DevActiveModel> selectDevActiveModelByCondtition(Integer currentPage, Integer pageSize, Map<String, String> condition);

    int insert(Dev dev);

    int updateByPrimaryKeySelective(Dev dev);

    DevAmountModel selectDevAmountModel(Map<String, String> condition);


    Dev selectDevById(Dev dev);

    List<DevInfo> selectFireList(Dev dev);

    DevInfo selectFireDev(DevInfo devInfo);

    int selectCountByType(Map<String, String> condition);


    List<Dev> selectDevByRegion(Integer currentPage, Dev dev);

    List<DevInfo> selectDevInfoByCondition(Map<String, String> condition);

    List<DevInfo> selectCoByCondition(Map<String, String> condition);

    List<DevInfo> selectDevNowByCondition(Map<String, String> condition);

    List<DevInfo> selectHydrantList(Map<String, String> condition, Integer currentPage, Integer pageSize);

    List<DevInfo> selectCh4ByCondition(Map<String, String> condition);

    List<DevInfo> selectDustByCondition(Map<String, String> condition);

    List<DevInfo> selectPressNowByCondition(Map<String, String> condition);

    List<DevInfo> selectFlowNowByCondition(Map<String, String> condition);

    List<DevInfo> selectTemNowByCondition(Map<String, String> condition);

    Integer selectHydrantCount(Map<String, String> condition);

    List<DevInfo> selectDevInfoByDevid(Map<String, String> condition);

    List<DevInfo> selectFanInfo(Map<String, String> condition);

    List<DevActiveInfo> selectCountOpen(Map<String, String> condition);

    JsonResult updatefan(Map<String, String> condition);

    List<DevInfo> selectDevList(Map<String, String> condition);

    //参数设置的设备树
    List<Dev> selectDevPByRegion(Integer currentPage, Dev dev);

    Integer selectDevCountByType(Map<String, String> condition);

    JsonResult updatehydrant(Map<String, String> condition);
}
