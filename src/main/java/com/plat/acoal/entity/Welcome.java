package com.plat.acoal.entity;

public class Welcome {
    private Integer id;

    private Integer icustomerid;

    private String info;

    private Integer isused;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIcustomerid() {
        return icustomerid;
    }

    public void setIcustomerid(Integer icustomerid) {
        this.icustomerid = icustomerid;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info == null ? null : info.trim();
    }

    public Integer getIsused() {
        return isused;
    }

    public void setIsused(Integer isused) {
        this.isused = isused;
    }
}