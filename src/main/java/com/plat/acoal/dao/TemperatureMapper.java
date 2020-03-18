package com.plat.acoal.dao;
import com.plat.acoal.entity.Temperature;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.TemperatureInfo;
import java.util.List;
public interface TemperatureMapper {
    int deleteByPrimaryKey(Integer id);
    int insert(Temperature record);
    int insertSelective(Temperature record);
    Temperature selectByPrimaryKey(Integer id);
    int updateByPrimaryKeySelective(Temperature record);
    int updateByPrimaryKey(Temperature record);
    List<TemperatureInfo> selectNewFtById(TemperatureInfo temperatureInfo);
  //  List<Temperature> selectFtByTime(TemperatureInfo temperatureInfo);
    List<Temperature> selectHFtByTime(TemperatureInfo temperatureInfo);
//    List<Temperature> selectFtByMinute(TemperatureInfo temperatureInfo);
    List<Temperature> selectFtByHour(TemperatureInfo temperatureInfo);
    List<DevInfo> selectFtList(DevInfo devInfo);
}