package com.plat.acoal.model;

import java.util.Date;

public class OperationIAO {
    /**
     * @ 用于查询操作日志传进去和输出来的参数
     */
    private Date operationdate;
    private String username;
    private String dcollectstart;
    private String dcollectend;
    private Integer status;
    private String taction;
    private String turl;
    private String turlname;

    public String getTurlname() {
        return turlname;
    }

    public void setTurlname(String turlname) {
        this.turlname = turlname;
    }

    public Date getOperationdate() {
        return operationdate;
    }

    public void setOperationdate(Date operationdate) {
        this.operationdate = operationdate;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDcollectstart() {
        return dcollectstart;
    }

    public void setDcollectstart(String dcollectstart) {
        this.dcollectstart = dcollectstart;
    }

    public String getDcollectend() {
        return dcollectend;
    }

    public void setDcollectend(String dcollectend) {
        this.dcollectend = dcollectend;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTaction() {
        return taction;
    }

    public void setTaction(String taction) {
        this.taction = taction;
    }

    public String getTurl() {
        return turl;
    }

    public void setTurl(String turl) {
        this.turl = turl;
    }
}
