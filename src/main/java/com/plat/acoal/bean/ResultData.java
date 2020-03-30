package com.plat.acoal.bean;
import com.plat.acoal.entity.Dev;
import lombok.Data;
import java.util.List;
import java.util.Map;

@Data
public class ResultData {
    private int code=0;
    private String msg="";
    private int count=0;
    private int pageno=0;
    private int devcount=0;
    private int pagecount=0;
    private List data=null;
    private List datalst2=null;
    private List datalst3=null;
    private List datalst4=null;
    private List datalst5=null;
    private String strdata=null;
    private Object info=null;
    private double[] arrddata1;
    private double[] arrddata2;
    private double[] arrddata3;
    private String[] arrsdata1;
    private String[] arrsdata2;
    private String[] arrsdata3;
    private String[] arrsdatax1;
    private String[] arrsdatax2;
    private String[] arrsdatax3;
    private int[] arridata1;
    private int[] arridata2;
    private int[] arridata3;
    private Dev dev;
    private Map<String,String> param =null;
    private String Date;
}
