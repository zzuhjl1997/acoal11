package com.plat.acoal.entity;

import java.util.Date;

public class Pressure {
    private Long id;

    private Date dcollectdt;

    private Float tpressure;

    private Integer devId;

    private Integer hydrantId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getDcollectdt() {
        return dcollectdt;
    }

    public void setDcollectdt(Date dcollectdt) {
        this.dcollectdt = dcollectdt;
    }

    public Float getTpressure() {
        return tpressure;
    }

    public void setTpressure(Float tpressure) {
        this.tpressure = tpressure;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }

    public Integer getHydrantId() {
        return hydrantId;
    }

    public void setHydrantId(Integer hydrantId) {
        this.hydrantId = hydrantId;
    }
}