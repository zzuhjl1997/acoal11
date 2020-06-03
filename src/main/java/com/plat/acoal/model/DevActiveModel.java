package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;

@Data
public class DevActiveModel {
    //t_dev_active.id
    private Integer devActiveId;
    //dev.id
    private Integer devId;
    //dev.name
    private String devName;
    //dev_type.id
    private Integer devTypeId;
    //dev_type.name
    private String devTypeName;
    //region.name
    private String regionName;
    //region.id
    private Integer regionId;
    //dev.site
    private String devSite;
    //user.iUserID
    private Integer userId;
    //user.cUserName
    private String userName;
    private String actflg;
    //dev_active.close_time
    private Date devActiveCloseTime;
    private Date devActiveOpenTime;
}
