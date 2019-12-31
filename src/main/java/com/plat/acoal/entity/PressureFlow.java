package com.plat.acoal.entity;

import java.util.Date;

public class PressureFlow {
    private Long id;

    private Date dcollectdt;

    private Float tflow;

    private Float tpressure;

    private Float tfan;

    private Integer devId;

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

    public Float getTpressure() {
        return tpressure;
    }

    public void setTpressure(Float tpressure) {
        this.tpressure = tpressure;
    }

    public Float getTfan() {
        return tfan;
    }

    public void setTfan(Float tfan) {
        this.tfan = tfan;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }
}