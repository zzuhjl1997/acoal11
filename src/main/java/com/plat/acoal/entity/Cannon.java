package com.plat.acoal.entity;

import java.util.Date;

public class Cannon {
    private Long id;

    private Integer devId;

    private Date collectdt;

    private Integer isfire;

    private Integer isfault;

    private Integer isopen;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Date getCollectdt() {
        return collectdt;
    }

    public void setCollectdt(Date collectdt) {
        this.collectdt = collectdt;
    }

    public Integer getIsfire() {
        return isfire;
    }

    public void setIsfire(Integer isfire) {
        this.isfire = isfire;
    }

    public Integer getIsfault() {
        return isfault;
    }

    public void setIsfault(Integer isfault) {
        this.isfault = isfault;
    }

    public Integer getIsopen() {
        return isopen;
    }

    public void setIsopen(Integer isopen) {
        this.isopen = isopen;
    }
}