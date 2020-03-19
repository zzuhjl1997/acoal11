package com.plat.acoal.controller;
import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Temperature;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.TemperatureInfo;
import com.plat.acoal.service.impl.TemperatureServiceImpl;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import net.sf.jsqlparser.expression.StringValue;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@Log4j2
@RequestMapping(value = "/temperature", produces = "application/json;charset=UTF-8")
public class TemperatureController {
    @Autowired
    public TemperatureServiceImpl temperatureServiceImpl;
    /**
     * 查询最新温度
     *
     * @param
     * @return
     */
    @GetMapping("/newFt")
    public String selectNewFt(TemperatureInfo temperatureInfo, HttpServletRequest request) {
        String devid = "3";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        temperatureInfo.setDevid(Integer.parseInt(devid));
        List<TemperatureInfo> lstft = new ArrayList<TemperatureInfo>();
        List<TemperatureInfo> newFt = temperatureServiceImpl.selectNewFtById(temperatureInfo);
        String[] time={};
        int pos=0;
        ResultData resultData=new ResultData();
        for (TemperatureInfo t:newFt
             ) {
            Date dt=t.getDcollectdt();
            t.setDcollectdt_re(DateUtil.dateToString(dt));
        }
        return JSON.toJSONString(newFt);
    }
    /**
     * 查询某天的温度
     *
     * @param temperatureInfo
     * @return
     */
    @GetMapping("/monitordayFt")
    public String getDayFt(TemperatureInfo temperatureInfo, HttpServletRequest request) {
        String devid = "3";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        String startdate = "", enddate = "";
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        SimpleDateFormat dfhour = new SimpleDateFormat("HH:mm");
        Date date = new Date();
        startdate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 00:00:00";
        enddate = DateUtil.dateToString(date, "yyyy-MM-dd") + " 23:59:59";
        if (request.getParameter("startdate") != null && !"".equals(request.getParameter("startdate"))) {
            startdate = request.getParameter("startdate");
            startdate += " 00:00:00";
        }
        if (request.getParameter("enddate") != null && !"".equals(request.getParameter("enddate"))) {
            enddate = request.getParameter("enddate");
            enddate += " 23:59:59";
        }
        double[] ftArr = new double[24];
        String[] arrhours = new String[24];// {"","","","","","","","","","","","","","","","","","","","","","","",""};
        for (int i = 0; i < 24; i++) {
//            arrhours[i] = String.valueOf(i)+":00";
            arrhours[i] = String.valueOf(i);
        }
        int pos = 0;
        temperatureInfo.setDevid(Integer.parseInt(devid));
        temperatureInfo.setDcollectstart(startdate);
        temperatureInfo.setDcollectend(enddate);
        ResultData resultData = new ResultData();
        List<Temperature> newFt = temperatureServiceImpl.selectFtByHour(temperatureInfo);
        for (Temperature item : newFt) {
            Date dt = item.getDcollectdt();
            if (item.getFt() != null && pos < 24) {
                ftArr[pos] = item.getFt();
                arrhours[pos] = dfhour.format(dt);
            }
            pos++;
        }
        resultData.setArrddata1(ftArr);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);
    }
    /**
     * 温度监控列表
     * @param devInfo
     * @param request
     * @return
     */
    @GetMapping("/ftList")
    public String getCoList(DevInfo devInfo, HttpServletRequest request, HttpSession session,@RequestParam Map<String,String> condition) throws ParseException {
        Integer icustomerid=null;
        if(session.getAttribute("icustomerid")!=null&&!"".equals(session.getAttribute("icustomerid"))){
            icustomerid=Integer.parseInt(session.getAttribute("icustomerid").toString());
        }
        Integer currentPage = 1;
        Integer pageSize = 1;

        if (condition.containsKey("currentPage")) {
//            System.out.println("哈瞌睡的感觉啊上的杰卡斯感到恐惧");
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? 1 : Integer.valueOf(condition.get("pageSize"));
            condition.remove("currentPage");
            condition.remove("pageSize");
        } else {
            currentPage = null;
            pageSize=null;
        }


        devInfo.setType(2);
        List<DevInfo> listinfo = temperatureServiceImpl.selectFtList(devInfo,currentPage,pageSize);
        int count=0;
        for (DevInfo item:listinfo) {
            count ++;
            item.setCount(count);
            item.setLastTime(DateUtil.dateToString(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            System.out.println("更新时间"+DateUtil.dateToString(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            System.out.println("对象"+item);
        }
        return JSON.toJSONString(listinfo);
    }
}