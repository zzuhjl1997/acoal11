package com.plat.acoal.service;


import com.github.pagehelper.PageInfo;
import com.plat.acoal.model.*;
import com.plat.acoal.utils.JsonResult;

import java.util.List;
import java.util.Map;

public interface AlarmService {
    PageInfo<AlarmModel> selectAlarmModelByCondition(Integer currentPage, Integer pageSize,Map<String,String> condition);
    int deleteByPrimaryKey(Long id);
    List<AlarmStatisticsModel> selectAlarmStatisticsModelByCondition(Map<String,String> condition);
    List<AlarmTypeModel> selectAlarmTypeModel();
    List<AlarmGradeModel> selectAlarmGradeModel();
    List<AlarmRatioModel> selectAlarmRatioModel();
    List<DevAlarmModel> selectDevAlarmModel(Map<String, String> condition);
    List<DevAlarmFrequencyModel> selectDevAlarmFrequencyModel(Map<String, String> condition);
    List<DevAlarmInfoModel> selectDevAlarmInfoModel(Map<String, String> condition);
    List<AlarmFourModel> selectAlarmFourCountModel();
    TodayAlarmAmountModel selectTodayAlarmAmountModel(Map<String ,String> condition);
    AlarmAmountModel selectAlarmAmountModel(Map<String ,String> condition);
    TodayUntreatedAlarmValueModel selectTodayAlarmUntreatedModel(Map<String ,String> condition);
    UntreatedAlarmValueModel selectAlarmUntreatedModel(Map<String ,String> condition);
    List<UntreatedAlarmModel> selectUntreatedAlarmModel(Map<String,String> condition);
    JsonResult updateUntreatedAlarmStatus(Map<String,String> condition);
    List<String> selectVoiceAlarm();

    List<AlarmPopupModel> selectAlarmPopupModel();
    List<AlarmInfo> selectAlarmInfoByCondition(AlarmInfo alarmInfo) ;
    int selectAlarmCount(Map<String, String> condition);
}
