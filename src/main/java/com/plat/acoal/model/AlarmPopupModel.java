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
}
