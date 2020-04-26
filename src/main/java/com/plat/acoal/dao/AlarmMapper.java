package com.plat.acoal.dao;
import com.plat.acoal.entity.Alarm;
import com.plat.acoal.model.AlarmInfo;
import com.plat.acoal.model.AlarmModel;
import java.util.List;
import java.util.Map;
public interface AlarmMapper {
    int deleteByPrimaryKey(Long id);
    int insert(Alarm record);
    int insertSelective(Alarm record);
    Alarm selectByPrimaryKey(Long id);
    int updateByPrimaryKeySelective(Alarm record);
    int updateByPrimaryKey(Alarm record);
    List<AlarmModel> selectAlarmModelByCondition(Map<String, String> condition);
    List<AlarmInfo> selectAlarmInfoByCondition(AlarmInfo alarmInfo);

    int selectAlarmCount(Map<String, String> condition);
}