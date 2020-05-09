package com.plat.acoal.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Parameter {
    private Long id;

    private Integer icustomerid;

    private Integer devId;

    private Boolean isused;

    private String cparam;

    private String cvalue;

    private Integer gradeid;

    private String cretime;

    private Integer idelaytime;

    private String corder;

    private Integer type;

    private Date adddatetime;

    private Date updatedatetime;

    private Integer adduserid;
    private Integer cvalue2;
}