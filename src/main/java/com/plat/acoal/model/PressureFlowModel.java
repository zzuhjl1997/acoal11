package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;
@Data
public class PressureFlowModel {
    private String dcollectstart;
    private String dcollectend;
    private Float tflow;
    private Float tpressure;
    private Float tfan;
    private String dcollectdt_re;
    private Date dcollectdt;
    private int devid;
    private Integer hydrantId;
    private Integer pressureid;
    private Integer flowid;

}
