package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;

@Data
public class CustomerInfo {
    private Integer id;
    private Integer icustomerid;
    private String ccustomername;
    private String ccustomeraddress;
    private String coperationname;
    private String coperationphone;
    private String clogopath;
    private Date dinstalldate;
    private Date dsafestartdate;
    private Date dexpaireddate;
    private Integer agent;
    private Double latitude;
    private Double longitude;
    private Double latitudetx;
    private Double longitudetx;
    private Byte usesms;
    private Integer leftsmsnums;
    private String memo;
    private Double buildingarea;
    private String firedevice;
    private String cshortname;
    private String province;
    private String city;
    private String district;
    private String qyperson;
    private String qymobile;
}
