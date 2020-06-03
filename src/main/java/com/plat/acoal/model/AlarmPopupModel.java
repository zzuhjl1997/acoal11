package com.plat.acoal.model;

import lombok.Data;

/**
 * @author : LiuYang | Leon Willow
 * @date : 2020-06-01 17:38
 * @des ：
 **/
@Data
public class AlarmPopupModel {
    private String devName;
    private String alarmValue;
    private String alarmThreshold;
    private String alarmTime;
    private String devRegion;
    private Integer times;

    @Override
    public String toString() {
        return alarmTime + " " + devName + "发生报警，当前数值" + alarmValue + "，超过设定最大值" + alarmThreshold + "。持续次数" + times + "，请及时处理。";
    }
}
