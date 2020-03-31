package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;
@Data
public class PressureFlowModel {
    private String dcollectstart;
    private String dcollectend;
    private Double tflow;
    private Double tpressure;
    private Float tfan;
    private String dcollectdt_re;
    private Date dcollectdt;
    private int devid;
    private Integer hydrantId;
    private Integer pressureid;
    private Integer flowid;
    private  Integer sequence;

}
