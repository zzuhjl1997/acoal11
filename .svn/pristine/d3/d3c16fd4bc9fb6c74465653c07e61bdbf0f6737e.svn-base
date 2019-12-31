package com.plat.acoal.dao;

import com.plat.acoal.entity.Dev;
import com.plat.acoal.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface DevMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Dev record);

    int insertSelective(Dev record);

    Dev selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Dev record);

    int updateByPrimaryKey(Dev record);

    List<DevNameModel> selectNameByCondition(Map<String, String> condition);
    List<DevTypeModel> selectTypeByCondition(Map<String, String> condition);
    List<DevInfoModel> selectDevInfoByCondition(Map<String, String> condition);
    List<DevActiveModel> selectDevActiveModelByCondition(Map<String, String> condition);

    List<Dev> selectDevByCustomerId(Integer icustomerid,Integer type);

    List<Dev> selectDevByRegionId(Integer regionid);

    List<DevInfo> selectFireByCondition(Dev dev);

    DevInfo selectFireById(DevInfo devInfo);

    int selectCountByType(int type);
}