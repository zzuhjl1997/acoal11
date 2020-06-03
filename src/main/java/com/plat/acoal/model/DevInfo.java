package com.plat.acoal.model;

import com.plat.acoal.utils.DateUtil;
import lombok.Data;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Data
public class DevInfo {

    private Integer id;
    //序号
    private Integer count;
    private Integer devcount;
    private Integer devcountonline;

    private Integer code;

    private Integer icustomerid;

    private String region;
    private String regionname;
    private String value;
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
    private Double fT;
    private Double tflow;
    private Double tpressure;

    private String typename;

    private String devname;

    private Double gco;

    private Double gch4;

    private Double go2;

    private Double fdust;

    private Double power;

    private Integer alarm;

    private Integer is_auto;
    private Integer pressureid;
    private Integer flowid;
    private Integer online;
    private Integer devid;
    private Integer rotate;
    private Integer isopen;
    private Integer isfire;
    private Integer isfault;
    //总时间
    private String sumtime;
    private String ser_num;
    //所有的在线率
    private List<Map<String,String>> mapList;

    //消防炮状态
    private Integer canno_status;


}
