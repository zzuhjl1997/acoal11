
package com.plat.acoal.model;

import lombok.Data;

import java.util.Date;

@Data
public class DevActiveInfo {
    private Integer id;
    private Integer devId;
    private Integer user;
    private Integer num;
    private String time;
    private Date openTime;
    private Date closeTime;
}