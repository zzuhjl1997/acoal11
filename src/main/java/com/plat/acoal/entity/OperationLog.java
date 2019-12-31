package com.plat.acoal.entity;

import java.util.Date;

public class OperationLog {
    private Integer id;

    private Integer status;

    private Integer operationuserid;

    private Date operationdate;

    private String taction;

    private String turl;

    private String turlname;

    private String ip;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getOperationuserid() {
        return operationuserid;
    }

    public void setOperationuserid(Integer operationuserid) {
        this.operationuserid = operationuserid;
    }

    public Date getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    public String getTaction() {
        return taction;
    }

    public void setTaction(String taction) {
        this.taction = taction == null ? null : taction.trim();
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl == null ? null : turl.trim();
    }

    public String getTurlname() {
        return turlname;
    }

    public void setTurlname(String turlname) {
        this.turlname = turlname == null ? null : turlname.trim();
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip == null ? null : ip.trim();
    }
}