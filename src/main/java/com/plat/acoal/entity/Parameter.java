package com.plat.acoal.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Parameter {
    private Long id;

    private Integer icustomerid;

    private Boolean isused;

    private String cparam;

    private String corder;

    private String cvalue;

    private Integer gradeid;

    private String cretime;

    private Integer type;

    private Integer devId;

    private Date adddatetime;

    private Date updatedatetime;

    private Integer adduserid;
//    private Integer ideviceid;


}