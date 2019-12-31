package com.plat.acoal.entity;

public class Dept {
    private Integer id;

    private Integer icustomerid;

    private Integer ideptid;

    private String deptname;

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

    public Integer getIdeptid() {
        return ideptid;
    }

    public void setIdeptid(Integer ideptid) {
        this.ideptid = ideptid;
    }

    public String getDeptname() {
        return deptname;
    }

    public void setDeptname(String deptname) {
        this.deptname = deptname == null ? null : deptname.trim();
    }
}