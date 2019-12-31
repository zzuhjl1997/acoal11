package com.plat.acoal.entity;

import java.util.Date;

public class RealCannonData {
    private Integer id;

    private Integer devid;

    private Date collectdt;

    private Integer isfire;

    private Integer isfault;

    private Integer isopen;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getDevid() {
        return devid;
    }

    public void setDevid(Integer devid) {
        this.devid = devid;
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