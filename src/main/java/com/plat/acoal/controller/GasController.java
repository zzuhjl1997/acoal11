package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.GasModel;
import com.plat.acoal.service.impl.GasServiceImpl;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = "/gas", produces = "application/json;charset=UTF-8")
public class GasController {
    @Autowired
    public GasServiceImpl gasServiceImpl;

    /**
     * 查询最新Ch4
     *
     * @param
     * @return
     */
    @GetMapping(value = "/newCh4", produces = "application/json;charset=UTF-8")
    public String selectNewFt(GasModel gasModel, HttpServletRequest request) {
        String devid = "7";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        gasModel.setDevid(Integer.parseInt(devid));

        List<GasModel> newGas = gasServiceImpl.selectNewCh4ById(gasModel);
        for (GasModel g : newGas
        ) {
            Date dt = g.getDcollectdt();
            g.setDcollectdt_re(DateUtil.dateToString(dt));
        }
        return JSON.toJSONString(newGas);
    }



    /**
     * 查询最新Co
     *
     * @param
     * @return
     */
    @GetMapping(value = "/newCo", produces = "application/json;charset=UTF-8")
    public String getNewCo(GasModel gasModel, HttpServletRequest request) {
        String devid = "7";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        gasModel.setDevid(Integer.parseInt(devid));

        List<GasModel> newGas = gasServiceImpl.selectNewCoById(gasModel);
        for (GasModel g : newGas
        ) {
            Date dt = g.getDcollectdt();
            g.setDcollectdt_re(DateUtil.dateToString(dt));
        }
        return JSON.toJSONString(newGas);
    }


    /**
     * 查询某天的ch4
     *
     * @param gasModel
     * @return
     */
    @GetMapping("/dayCh4")
    public String getDayFt(GasModel gasModel, HttpServletRequest request) {
        String devid = "7";
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
        double[] fGch4Arr = new double[24];
        double[] fGcoArr = new double[24];
        double[] fGo2Arr = new double[24];
        String[] arrhours = new String[24];// {"","","","","","","","","","","","","","","","","","","","","","","",""};
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                arrhours[i] = "0" + String.valueOf(i) + ":00";
            } else {
                arrhours[i] = String.valueOf(i) + ":00";
            }
        }
        int pos = 0;
        gasModel.setDevid(Integer.parseInt(devid));
        gasModel.setDcollectstart(startdate);
        gasModel.setDcollectend(enddate);

        List<GasModel> newGas = gasServiceImpl.selectCh4ByHour(gasModel);
        ResultData resultData = new ResultData();
        for (GasModel item : newGas) {
            Date dt = item.getDcollectdt();
            arrhours[pos] = dfhour.format(dt);
            if (item.getGch4() != null && pos < 24) {
                fGch4Arr[pos] = item.getGch4();
            }
            if (item.getGco() != null && pos < 24) {
                fGcoArr[pos] = item.getGch4();
            }
            if (item.getGo2() != null && pos < 24) {
                fGo2Arr[pos] = item.getGo2();
            }
            pos++;


        }
        resultData.setArrddata1(fGch4Arr);
        resultData.setArrddata2(fGcoArr);
        resultData.setArrddata3(fGo2Arr);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);
    }
    /**
     * 查询某天的co
     *
     * @param gasModel
     * @return
     */
    @GetMapping("/dayCo")
    public String getDayCo(GasModel gasModel, HttpServletRequest request) {
        String devid = "7";
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
        double[] fGch4Arr = new double[24];
        double[] fGcoArr = new double[24];
        double[] fGo2Arr = new double[24];
        String[] arrhours = new String[24];// {"","","","","","","","","","","","","","","","","","","","","","","",""};
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                arrhours[i] = "0" + String.valueOf(i) + ":00";
            } else {
                arrhours[i] = String.valueOf(i) + ":00";
            }
        }
        int pos = 0;
        gasModel.setDevid(Integer.parseInt(devid));
        gasModel.setDcollectstart(startdate);
        gasModel.setDcollectend(enddate);

        List<GasModel> newGas = gasServiceImpl.selectCoByHour(gasModel);
        ResultData resultData = new ResultData();
        for (GasModel item : newGas) {
            Date dt = item.getDcollectdt();
            arrhours[pos] = dfhour.format(dt);
            if (item.getGch4() != null && pos < 24) {
                fGch4Arr[pos] = item.getGch4();
            }
            if (item.getGco() != null && pos < 24) {
                fGcoArr[pos] = item.getGch4();
            }
            if (item.getGo2() != null && pos < 24) {
                fGo2Arr[pos] = item.getGo2();
            }
            pos++;
        }
        resultData.setArrddata1(fGch4Arr);
        resultData.setArrddata2(fGcoArr);
        resultData.setArrddata3(fGo2Arr);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);
    }

    /**
     * ch4监控列表
     * @param devInfo
     * @param request
     * @return
     */
    @GetMapping("/ch4List")
    public String getMonitorList(DevInfo devInfo, HttpServletRequest request) {


        ResultData resultData = new ResultData();
        List<DevInfo> listinfo = gasServiceImpl.selectCh4List(devInfo);
        int count=0;
        for (DevInfo item:listinfo
        ) {
            count ++;
            item.setCount(count);
        }
        return JSON.toJSONString(listinfo);

    }
    /**
     * co监控列表
     * @param devInfo
     * @param request
     * @return
     */
    @GetMapping("/coList")
    public String getCoList(DevInfo devInfo, HttpServletRequest request) {


        List<DevInfo> listinfo = gasServiceImpl.selectCoList(devInfo);
        int count=0;
        for (DevInfo item:listinfo
        ) {
            count ++;
            item.setCount(count);
        }
        return JSON.toJSONString(listinfo);
    }
}
