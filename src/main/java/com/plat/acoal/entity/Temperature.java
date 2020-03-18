package com.plat.acoal.entity;

import lombok.Data;

import java.util.Date;
@Data
public class Temperature {
    private Integer id;

    private Date dcollectdt;

    private Float ft;

    private Integer devid;


}