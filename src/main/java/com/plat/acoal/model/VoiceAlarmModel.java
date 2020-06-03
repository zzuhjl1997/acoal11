package com.plat.acoal.model;

import lombok.Data;

/**
 * @author : LiuYang | Leon Willow
 * @date : 2020-06-01 17:18
 * @des ：
 **/
@Data
public class VoiceAlarmModel {
    private String devName;
    private String alarmValue;
    private String alarmThreshold;

    @Override
    public String toString() {
        return devName + "当前数值：" + alarmValue + "，超过设定最大值" + alarmThreshold + "，请立即处理";
    }
}
