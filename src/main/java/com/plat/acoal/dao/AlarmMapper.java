package com.plat.acoal.dao;

import com.plat.acoal.entity.Alarm;
import com.plat.acoal.model.*;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface AlarmMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Alarm record);

    int insertSelective(Alarm record);

    Alarm selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Alarm record);

    int updateByPrimaryKey(Alarm record);

    List<AlarmModel> selectAlarmModelByCondition(Map<String, String> condition);

    List<AlarmStatisticsModel> selectAlarmStatisticsModelByCondition(Map<String, String> condition);

    List<AlarmTypeModel> selectAlarmTypeModel();

    List<AlarmGradeModel> selectAlarmGradeModel();

    List<AlarmRatioModel> selectAlarmRatioModel();

    List<DevAlarmModel> selectDevAlarmModel(Map<String, String> condition);

    List<DevAlarmFrequencyModel> selectDevAlarmFrequencyModel(Map<String, String> condition);

    List<DevAlarmInfoModel> selectDevAlarmInfoModel(Map<String, String> condition);

    List<AlarmFourModel> selectAlarmFourCountModel();

    TodayAlarmAmountModel selectTodayAlarmAmountModel(Map<String, String> condition);

    AlarmAmountModel selectAlarmAmountModel(Map<String, String> condition);

    TodayUntreatedAlarmValueModel selectTodayAlarmUntreatedModel(Map<String, String> condition);

    UntreatedAlarmValueModel selectAlarmUntreatedModel(Map<String, String> condition);

    List<UntreatedAlarmModel> selectUntreatedAlarmModel(Map<String,String> condition);

    int updateUntreatedAlarmStatus(Map<String,String> condition);
    List<AlarmInfo> selectAlarmInfoByCondition(AlarmInfo alarmInfo);
    int selectAlarmCount(Map<String, String> condition);


    List<VoiceAlarmModel> selectVoiceAlarm();

    List<AlarmPopupModel> selectAlarmPopupModel();

}