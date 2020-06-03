package com.plat.acoal.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plat.acoal.dao.AlarmMapper;
import com.plat.acoal.model.*;
import com.plat.acoal.service.AlarmService;
import com.plat.acoal.utils.DateUtil;
import com.plat.acoal.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Stream;

@Service
public class AlarmServiceImpl implements AlarmService {
    @Autowired
    private AlarmMapper am;

    @Override
    public PageInfo<AlarmModel> selectAlarmModelByCondition(Integer currentPage, Integer pageSize, Map<String, String> condition) {
        Optional.ofNullable(currentPage).ifPresent(a -> PageHelper.startPage(a, pageSize));
        List<AlarmModel> list = am.selectAlarmModelByCondition(condition);
        return new PageInfo<>(list);
    }

    @Override
    public int deleteByPrimaryKey(Long id) {
        return am.deleteByPrimaryKey(id);
    }

    @Override
    public List<AlarmStatisticsModel> selectAlarmStatisticsModelByCondition(Map<String, String> condition) {
        List<String> list = DateUtil.getAllDaysIn(condition.get("alarmTimeHead"), condition.get("alarmTimeTail"), "yyyy-MM-dd");
        List<AlarmStatisticsModel> asmList = am.selectAlarmStatisticsModelByCondition(condition);
        Map<String, String> map = new HashMap<>();
        if(asmList.isEmpty()){
            AlarmStatisticsModel asm = new AlarmStatisticsModel();
            ArrayList<String> list1 = new ArrayList<>();
            list.forEach((b) -> list1.add(map.getOrDefault(b, "0")));
            asm.setXaxis(list);
            asm.setYaxis(list1);
            asmList.add(asm);
            return asmList;
        }
        asmList.forEach((a) -> {
            ArrayList<String> list1 = new ArrayList<>();
            int i = 0;
            for (String item : a.getXaxis()) {
                map.put(item, a.getTempY().get(i).getValue());
                i++;
            }
            list.forEach((b) -> list1.add(map.getOrDefault(b, "0")));
            a.setXaxis(list);
            a.setYaxis(list1);
            a.setTempY(null);
        });
        return asmList;
    }

    @Override
    public List<AlarmTypeModel> selectAlarmTypeModel() {
        return am.selectAlarmTypeModel();
    }

    @Override
    public List<AlarmGradeModel> selectAlarmGradeModel() {
        return am.selectAlarmGradeModel();
    }

    @Override
    public List<AlarmRatioModel> selectAlarmRatioModel() {
        return am.selectAlarmRatioModel();
    }

    @Override
    public List<DevAlarmModel> selectDevAlarmModel(Map<String, String> condition) {
        return am.selectDevAlarmModel(condition);
    }

    @Override
    public List<DevAlarmFrequencyModel> selectDevAlarmFrequencyModel(Map<String, String> condition) {
        return am.selectDevAlarmFrequencyModel(condition);
    }

    @Override
    public List<DevAlarmInfoModel> selectDevAlarmInfoModel(Map<String, String> condition) {
        return am.selectDevAlarmInfoModel(condition);
    }

    @Override
    public List<AlarmFourModel> selectAlarmFourCountModel() {
        return am.selectAlarmFourCountModel();
    }

    @Override
    public TodayAlarmAmountModel selectTodayAlarmAmountModel(Map<String, String> condition) {
        return am.selectTodayAlarmAmountModel(condition);
    }

    @Override
    public AlarmAmountModel selectAlarmAmountModel(Map<String, String> condition) {
        return am.selectAlarmAmountModel(condition);
    }

    @Override
    public TodayUntreatedAlarmValueModel selectTodayAlarmUntreatedModel(Map<String, String> condition) {
        return am.selectTodayAlarmUntreatedModel(condition);
    }

    @Override
    public UntreatedAlarmValueModel selectAlarmUntreatedModel(Map<String, String> condition) {
        return am.selectAlarmUntreatedModel(condition);
    }

    @Override
    public List<UntreatedAlarmModel> selectUntreatedAlarmModel(Map<String, String> condition) {
        return am.selectUntreatedAlarmModel(condition);
    }

    @Override
    public JsonResult updateUntreatedAlarmStatus(Map<String, String> condition) {
        JsonResult jr = new JsonResult();
        int iserror = am.updateUntreatedAlarmStatus(condition);
        if (iserror == 1) {
            jr.setMsg("处理完毕！");
            jr.setStatus(200);
        } else if (iserror < 1) {
            jr.setMsg("处理错误！");
            jr.setStatus(500);
        } else {
            jr.setStatus(555);
            jr.setMsg("处理异常！可能出现处理多个记录的情况！");
        }
        return jr;
    }

    @Override
    public List<String> selectVoiceAlarm() {
        List<String> returnList = new ArrayList<>();
        List<VoiceAlarmModel> voiceAlarmModels = am.selectVoiceAlarm();
        voiceAlarmModels.forEach(a->returnList.add(a.toString()));
        return returnList;
    }

    @Override
    public List<String> selectAlarmPopupModel(){
        List<String> returnList = new ArrayList<>();
        List<AlarmPopupModel> voiceAlarmModels = am.selectAlarmPopupModel();
        voiceAlarmModels.forEach(a->returnList.add(a.toString()));
        return returnList;
    }

    @Override
    public int selectAlarmCount(Map<String, String> condition) {
        return am.selectAlarmCount(condition);
    }
    @Override
    public List<AlarmInfo> selectAlarmInfoByCondition(AlarmInfo alarmInfo) {
        return am.selectAlarmInfoByCondition(alarmInfo);
    }


}
