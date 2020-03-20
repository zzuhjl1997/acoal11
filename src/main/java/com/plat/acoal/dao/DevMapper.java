package com.plat.acoal.dao;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.model.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
import javax.crypto.spec.DESedeKeySpec;
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
    int selectCountByType(Map<String, String> condition);
    List<Dev> selectDevInfoByCon(Dev dev);
    List<DevInfo> selectDevInfoByCus(Map<String, String> condition);
    List<DevInfo> selectDevByCondition(Map<String, String> condition);
    List<DevInfo> selectDevNowByCondition(Map<String, String> condition);

    List<DevInfo> selectHydrantList(Map<String, String> condition);

    List<DevInfo> selectCh4ByCondition(Map<String, String> condition);

    List<DevInfo> selectDustByCondition(Map<String, String> condition);

    List<DevInfo> selectPressNowByCondition(Map<String, String> condition);

    List<DevInfo> selectFlowNowByCondition(Map<String, String> condition);

    List<DevInfo> selectTemNowByCondition(Map<String, String> condition);
}