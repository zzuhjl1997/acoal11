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
    private String alarmTime;
    private Integer alarmCount;
    private Integer alarmStatus;
    private Integer alarmGradeId;
    private String alarmGradeName;
    private Integer devId;
    private String devName;
    private Integer regionId;
    private String regionName;
    private String devSite;
    private Integer devStatus;
    private String alarmInfo;
    private Integer alarmDealUserId;
    private Date alarmDealTime;
    private String imgpath;
    private String videopath;
    private String lastalarmtime;
    private String lastimgpath;
    private String lastvideopath;
    private Integer isfault;
    private String dealimgpath;
}
