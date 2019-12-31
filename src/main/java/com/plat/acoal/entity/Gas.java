package com.plat.acoal.entity;

import java.util.Date;

public class Gas {
    private Long id;

    private Date dcollectdt;

    private Float gch4;

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

    public Float getGch4() {
        return gch4;
    }

    public void setGch4(Float gch4) {
        this.gch4 = gch4;
    }

    public Integer getDevId() {
        return devId;
    }

    public void setDevId(Integer devId) {
        this.devId = devId;
    }
}