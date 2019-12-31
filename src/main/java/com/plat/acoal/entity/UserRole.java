package com.plat.acoal.entity;

import java.util.Date;

public class UserRole {
    private Integer id;

    private String corder;

    private String ipopedom;

    private String cdspname;

    private Date createtime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCorder() {
        return corder;
    }

    public void setCorder(String corder) {
        this.corder = corder == null ? null : corder.trim();
    }

    public String getIpopedom() {
        return ipopedom;
    }

    public void setIpopedom(String ipopedom) {
        this.ipopedom = ipopedom == null ? null : ipopedom.trim();
    }

    public String getCdspname() {
        return cdspname;
    }

    public void setCdspname(String cdspname) {
        this.cdspname = cdspname == null ? null : cdspname.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }
}