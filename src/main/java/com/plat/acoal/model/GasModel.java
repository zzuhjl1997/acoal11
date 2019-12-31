package com.plat.acoal.model;


import java.util.Date;

public class GasModel {

    private String dcollectstart;
    private String dcollectend;

    private Float gco;

    private Float gch4;

    private Float go2;
    private String dcollectdt_re;
    private Date dcollectdt;
    private int devid;

    public String getDcollectdt_re() {
        return dcollectdt_re;
    }

    public void setDcollectdt_re(String dcollectdt_re) {
        this.dcollectdt_re = dcollectdt_re;
    }

    public Date getDcollectdt() {
        return dcollectdt;
    }

    public void setDcollectdt(Date dcollectdt) {
        this.dcollectdt = dcollectdt;
    }

    public Float getGco() {
        return gco;
    }

    public void setGco(Float gco) {
        this.gco = gco;
    }

    public Float getGch4() {
        return gch4;
    }

    public void setGch4(Float gch4) {
        this.gch4 = gch4;
    }

    public Float getGo2() {
        return go2;
    }

    public void setGo2(Float go2) {
        this.go2 = go2;
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



    public int getDevid() {
        return devid;
    }

    public void setDevid(int devid) {
        this.devid = devid;
    }
}
