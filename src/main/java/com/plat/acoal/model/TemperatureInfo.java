package com.plat.acoal.model;
import lombok.Data;

import java.util.Date;
@Data
public class TemperatureInfo {
    private String dcollectstart;
    private String dcollectend;
    private Float ft;
    private Date dcollectdt;
    private String dcollectdt_re;
    private int devid;
}
