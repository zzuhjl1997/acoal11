package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;

@Data
public class DustModel {
    private String dcollectstart;
    private String dcollectend;
    private Double fdust;

    private String dcollectdt_re;
    private Date dcollectdt;
    private int devid;


}
