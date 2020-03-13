package com.plat.acoal.service;

import com.plat.acoal.entity.Dev;
import com.plat.acoal.model.*;

import java.util.List;
import java.util.Map;

public interface DevService {
    List<DevNameModel> selectNameByCondition(Map<String, String> condition);
    List<DevTypeModel> selectTypeByCondition(Map<String, String> condition);
    List<DevInfoModel> selectDevInfoModelByCondtition(Integer currentPage, Map<String, String> condition);
    List<DevActiveModel> selectDevActiveModelByCondtition(Integer currentPage, Map<String, String> condition);
    Dev selectDevById(Dev dev);

    List<DevInfo> selectFireList(Dev dev);

    DevInfo selectFireDev(DevInfo devInfo);

    int selectCountByType(Map<String, String> condition);



    List<Dev> selectDevByRegion(Integer currentPage, Dev dev);

    List<DevInfo> selectDevInfoByCondition(Map<String, String> condition);

    List<DevInfo> selectDevByCondition(Map<String, String> condition);
}
