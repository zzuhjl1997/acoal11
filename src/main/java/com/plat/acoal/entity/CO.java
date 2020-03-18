package com.plat.acoal.entity;
import java.util.Date;
public class CO {
    private Long id;
    private Date dcollectdt;
    private Float gco;
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
    public Float getGco() {
        return gco;
    }
    public void setGco(Float gco) {
        this.gco = gco;
    }
    public Integer getDevId() {
        return devId;
    }
    public void setDevId(Integer devId) {
        this.devId = devId;
    }
}