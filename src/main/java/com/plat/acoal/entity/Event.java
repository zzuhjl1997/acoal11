package com.plat.acoal.entity;

import java.util.Date;

public class Event {
    private Long id;

    private Byte ieventtype;

    private Date doccurdt;

    private String calloccurdt;

    private Integer iaddress;

    private Integer idevgroup;

    private Byte iphase;

    private Integer idayoccurcnt;

    private String ctagfilter;

    private String ceventmsg;

    private Byte idealwithtype;

    private Byte isconfirm;

    private String cremark;

    private Byte ilevel;

    private Date ddealtime;

    private Integer idealuid;

    private Date updatetime;

    private Byte isfault;

    private Integer isread;

    private Double curvalue;

    private Double lmtvalue;

    private Integer ideviceid;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getIeventtype() {
        return ieventtype;
    }

    public void setIeventtype(Byte ieventtype) {
        this.ieventtype = ieventtype;
    }

    public Date getDoccurdt() {
        return doccurdt;
    }

    public void setDoccurdt(Date doccurdt) {
        this.doccurdt = doccurdt;
    }

    public String getCalloccurdt() {
        return calloccurdt;
    }

    public void setCalloccurdt(String calloccurdt) {
        this.calloccurdt = calloccurdt == null ? null : calloccurdt.trim();
    }

    public Integer getIaddress() {
        return iaddress;
    }

    public void setIaddress(Integer iaddress) {
        this.iaddress = iaddress;
    }

    public Integer getIdevgroup() {
        return idevgroup;
    }

    public void setIdevgroup(Integer idevgroup) {
        this.idevgroup = idevgroup;
    }

    public Byte getIphase() {
        return iphase;
    }

    public void setIphase(Byte iphase) {
        this.iphase = iphase;
    }

    public Integer getIdayoccurcnt() {
        return idayoccurcnt;
    }

    public void setIdayoccurcnt(Integer idayoccurcnt) {
        this.idayoccurcnt = idayoccurcnt;
    }

    public String getCtagfilter() {
        return ctagfilter;
    }

    public void setCtagfilter(String ctagfilter) {
        this.ctagfilter = ctagfilter == null ? null : ctagfilter.trim();
    }

    public String getCeventmsg() {
        return ceventmsg;
    }

    public void setCeventmsg(String ceventmsg) {
        this.ceventmsg = ceventmsg == null ? null : ceventmsg.trim();
    }

    public Byte getIdealwithtype() {
        return idealwithtype;
    }

    public void setIdealwithtype(Byte idealwithtype) {
        this.idealwithtype = idealwithtype;
    }

    public Byte getIsconfirm() {
        return isconfirm;
    }

    public void setIsconfirm(Byte isconfirm) {
        this.isconfirm = isconfirm;
    }

    public String getCremark() {
        return cremark;
    }

    public void setCremark(String cremark) {
        this.cremark = cremark == null ? null : cremark.trim();
    }

    public Byte getIlevel() {
        return ilevel;
    }

    public void setIlevel(Byte ilevel) {
        this.ilevel = ilevel;
    }

    public Date getDdealtime() {
        return ddealtime;
    }

    public void setDdealtime(Date ddealtime) {
        this.ddealtime = ddealtime;
    }

    public Integer getIdealuid() {
        return idealuid;
    }

    public void setIdealuid(Integer idealuid) {
        this.idealuid = idealuid;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public Byte getIsfault() {
        return isfault;
    }

    public void setIsfault(Byte isfault) {
        this.isfault = isfault;
    }

    public Integer getIsread() {
        return isread;
    }

    public void setIsread(Integer isread) {
        this.isread = isread;
    }

    public Double getCurvalue() {
        return curvalue;
    }

    public void setCurvalue(Double curvalue) {
        this.curvalue = curvalue;
    }

    public Double getLmtvalue() {
        return lmtvalue;
    }

    public void setLmtvalue(Double lmtvalue) {
        this.lmtvalue = lmtvalue;
    }

    public Integer getIdeviceid() {
        return ideviceid;
    }

    public void setIdeviceid(Integer ideviceid) {
        this.ideviceid = ideviceid;
    }
}