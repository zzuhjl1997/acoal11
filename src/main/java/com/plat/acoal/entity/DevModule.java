package com.plat.acoal.entity;
import java.util.Date;
public class DevModule {
    private Integer id;
    private Long moduleid;
    private String devaddress;
    private Integer customerid;
    private String simcard;
    private String protocoltype;
    private Date updatetime;
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Long getModuleid() {
        return moduleid;
    }
    public void setModuleid(Long moduleid) {
        this.moduleid = moduleid;
    }
    public String getDevaddress() {
        return devaddress;
    }
    public void setDevaddress(String devaddress) {
        this.devaddress = devaddress == null ? null : devaddress.trim();
    }
    public Integer getCustomerid() {
        return customerid;
    }
    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }
    public String getSimcard() {
        return simcard;
    }
    public void setSimcard(String simcard) {
        this.simcard = simcard == null ? null : simcard.trim();
    }
    public String getProtocoltype() {
        return protocoltype;
    }
    public void setProtocoltype(String protocoltype) {
        this.protocoltype = protocoltype == null ? null : protocoltype.trim();
    }
    public Date getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}