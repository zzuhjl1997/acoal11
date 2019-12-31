package com.plat.acoal.service;


import com.plat.acoal.entity.Alarm;
import com.plat.acoal.model.AlarmInfo;
import com.plat.acoal.model.AlarmModel;

import java.util.List;
import java.util.Map;

public interface AlarmService {
    List<AlarmModel> selectAlarmModelByCondition(Integer currentPage, Map<String, String> condition);
    int deleteByPrimaryKey(Long id);

    List<AlarmInfo> selectAlarmInfoByCondition(AlarmInfo alarmInfo);
}
