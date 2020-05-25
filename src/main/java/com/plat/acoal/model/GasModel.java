package com.plat.acoal.model;


import lombok.Data;

import java.util.Date;
import java.util.function.Predicate;

@Data
public class GasModel {

    private String dcollectstart;
    private String dcollectend;
    private String devname;
    private String site;
    private String ip;
    private String typename;
    private Date updatetime;
    private String lasttime;
    private Double gco;
    private Double gch4;
    private Double go2;
    private Double val;
    private String dcollectdt_re;
    private Date dcollectdt;
    private Integer devid;
    private Integer type;
    private Integer code;
    private Integer online;
    private Integer count;
//    private Predicate
}
