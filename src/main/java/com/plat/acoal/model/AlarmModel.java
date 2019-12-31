package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;

@Data
public class AlarmModel {
    private Integer alarmId;
    private Integer alarmTypeId;
    private String alarmTypeName;
    private Float alarmThreshold;
    private Float alarmData;
    private Date alarmTime;
    private Integer alarmCount;
    private Integer alarmDis;
    private Integer alarmGradeId;
    private String alarmGradeName;
    private String devName;
    private Integer regionId;
    private String regionName;
    private String devSite;
    private Integer devStatus;
    private String alarmInfo;
    private Integer alarmDealUserId;
    private Date alarmDealTime;
}
