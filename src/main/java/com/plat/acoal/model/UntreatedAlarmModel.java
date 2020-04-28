package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;

/**
 * @author : LiuYang | Leon Willow
 * @date : 2020-04-20 09:33
 * @des ：未处理报警
 **/
@Data
public class UntreatedAlarmModel {
    private Integer alarmId;
    private String alarmTypeName;
    private String alarmTime;
    private Integer alarmStatus;
    private String alarmGradeName;
    private String devName;
    private String regionName;
    private String devSite;
    private String alarmInfo;
    private String alarmDealTime;
}
