package com.plat.acoal.model;

import lombok.Data;

@Data
public class DevInfoModel {
    private Integer devId;
    private String devName;
    private Integer devTypeId;
    private String devTypeName;
    private Integer regionId;
    private String regionName;
    private String devSite;
    private Integer devStatus;
    private Integer devCode;
    private String devSerNum;
    private String devRemark;
}
