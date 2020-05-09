package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;

@Data
public class ParameterInfo {
    private Long id;

    private Integer icustomerid;

    private Integer devId;

    private Boolean isused;

    private String cparam;

    private String cvalue;

    private String cvalue2;

    private Integer gradeid;

    private String cretime;

    private Integer idelaytime;

    private String corder;
    private String Gas;

    private Integer type;

    private Date adddatetime;

    private Date updatedatetime;

    private Integer adduserid;
}
