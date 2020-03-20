package com.plat.acoal.entity;

public class HydrantidRelation {
    private Integer id;

    private Integer hydrantid;

    private Integer flowid;

    private Integer pressureid;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHydrantid() {
        return hydrantid;
    }

    public void setHydrantid(Integer hydrantid) {
        this.hydrantid = hydrantid;
    }

    public Integer getFlowid() {
        return flowid;
    }

    public void setFlowid(Integer flowid) {
        this.flowid = flowid;
    }

    public Integer getPressureid() {
        return pressureid;
    }

    public void setPressureid(Integer pressureid) {
        this.pressureid = pressureid;
    }
}