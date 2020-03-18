package com.plat.acoal.entity;
import java.util.Date;
public class Devcommu {
    private Integer id;
    private Integer icustomerid;
    private String cdevaddress;
    private Integer idevid;
    private Integer idevtype;
    private Integer idisplayorder;
    private String cdevname;
    private String cdevinstposition;
    private Byte ionline;
    private Date dlastonlinetime;
    private Long imoduleid;
    private Long imoduleidslave;
    private String meterno;
    private Integer iprotocoltype;
    private Double stdload;
    private String memo;
    private Date updatetime;
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
    public String getCdevaddress() {
        return cdevaddress;
    }
    public void setCdevaddress(String cdevaddress) {
        this.cdevaddress = cdevaddress == null ? null : cdevaddress.trim();
    }
    public Integer getIdevid() {
        return idevid;
    }
    public void setIdevid(Integer idevid) {
        this.idevid = idevid;
    }
    public Integer getIdevtype() {
        return idevtype;
    }
    public void setIdevtype(Integer idevtype) {
        this.idevtype = idevtype;
    }
    public Integer getIdisplayorder() {
        return idisplayorder;
    }
    public void setIdisplayorder(Integer idisplayorder) {
        this.idisplayorder = idisplayorder;
    }
    public String getCdevname() {
        return cdevname;
    }
    public void setCdevname(String cdevname) {
        this.cdevname = cdevname == null ? null : cdevname.trim();
    }
    public String getCdevinstposition() {
        return cdevinstposition;
    }
    public void setCdevinstposition(String cdevinstposition) {
        this.cdevinstposition = cdevinstposition == null ? null : cdevinstposition.trim();
    }
    public Byte getIonline() {
        return ionline;
    }
    public void setIonline(Byte ionline) {
        this.ionline = ionline;
    }
    public Date getDlastonlinetime() {
        return dlastonlinetime;
    }
    public void setDlastonlinetime(Date dlastonlinetime) {
        this.dlastonlinetime = dlastonlinetime;
    }
    public Long getImoduleid() {
        return imoduleid;
    }
    public void setImoduleid(Long imoduleid) {
        this.imoduleid = imoduleid;
    }
    public Long getImoduleidslave() {
        return imoduleidslave;
    }
    public void setImoduleidslave(Long imoduleidslave) {
        this.imoduleidslave = imoduleidslave;
    }
    public String getMeterno() {
        return meterno;
    }
    public void setMeterno(String meterno) {
        this.meterno = meterno == null ? null : meterno.trim();
    }
    public Integer getIprotocoltype() {
        return iprotocoltype;
    }
    public void setIprotocoltype(Integer iprotocoltype) {
        this.iprotocoltype = iprotocoltype;
    }
    public Double getStdload() {
        return stdload;
    }
    public void setStdload(Double stdload) {
        this.stdload = stdload;
    }
    public String getMemo() {
        return memo;
    }
    public void setMemo(String memo) {
        this.memo = memo == null ? null : memo.trim();
    }
    public Date getUpdatetime() {
        return updatetime;
    }
    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }
}