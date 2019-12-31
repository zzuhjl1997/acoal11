package com.plat.acoal.entity;

import java.util.Date;

public class Flow {
    private Long id;

    private Date dcollectdt;

    private Float tflow;

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

    public Float getTflow() {
        return tflow;
    }

    public void setTflow(Float tflow) {
        this.tflow = tflow;
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