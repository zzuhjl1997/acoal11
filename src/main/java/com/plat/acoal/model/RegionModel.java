package com.plat.acoal.model;

import com.plat.acoal.entity.Dev;
import com.plat.acoal.entity.Dust;
import com.plat.acoal.entity.Gas;

import java.util.Date;
import java.util.List;

public class RegionModel {
    private Integer id;

    private Integer icustomerid;

    private String name;

    private Date createTime;

    private Date updateTime;

    private String remark;

    // 设备集合
    private List<Dev> devs;

    // 可燃气体集合
    private List<Gas> gases;

    // 粉尘集合
    private List<Dust> dusts;

    public List<Dust> getDusts() {
        return dusts;
    }

    public void setDusts(List<Dust> dusts) {
        this.dusts = dusts;
    }

    public List<Gas> getGases() {
        return gases;
    }

    public void setGases(List<Gas> gases) {
        this.gases = gases;
    }

    public List<Dev> getDevs() {
        return devs;
    }

    public void setDevs(List<Dev> devs) {
        this.devs = devs;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}