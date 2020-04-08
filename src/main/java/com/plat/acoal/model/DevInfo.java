package com.plat.acoal.model;

import com.plat.acoal.utils.DateUtil;
import lombok.Data;

import java.text.DateFormat;
import java.util.Date;

@Data
public class DevInfo {

    private Integer id;
    //序号
    private Integer count;

    private Integer code;

    private Integer icustomerid;

    private Integer region;
    private String regionname;
    private String customername;

    private String site;

    private String name;

    private Integer type;

    private String ip;

    private String serNum;

    private String remark;

    private Date createTime;

    private Date updateTime;
    private String lastTime;
    private Integer status;
    private double fT;
    private Double tflow;
    private Double tpressure;

    private String typename;

    private String devname;

    private Float gco;

    private Float gch4;

    private Float go2;

    private Float fdust;

    private Float power;

    private Integer alarm;

    private Integer is_auto;
    private Integer pressureid;
    private Integer flowid;
    private Integer online;
    private Integer devid;
    private Integer rotate;
    //总时间
    private String sumtime;


}
