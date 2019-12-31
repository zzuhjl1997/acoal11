package com.plat.acoal.entity;

import java.util.Date;

public class WxUser {
    private Integer id;

    private String openidGzh;

    private String accessToken;

    private Integer customerid;

    private Integer userid;

    private Date updatetime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getOpenidGzh() {
        return openidGzh;
    }

    public void setOpenidGzh(String openidGzh) {
        this.openidGzh = openidGzh == null ? null : openidGzh.trim();
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken == null ? null : accessToken.trim();
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getUserid() {
        return userid;
    }

    public void setUserid(Integer userid) {
        this.userid = userid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}