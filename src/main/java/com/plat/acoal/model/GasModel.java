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
    private Float gco;
    private Float gch4;
    private Float go2;
    private Float val;
    private String dcollectdt_re;
    private Date dcollectdt;
    private Integer devid;
    private Integer type;
    private Integer code;
    private Integer online;
    private Integer count;
//    private Predicate
}
