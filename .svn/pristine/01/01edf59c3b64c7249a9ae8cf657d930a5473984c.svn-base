package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.model.DustModel;
import com.plat.acoal.service.impl.DustServiceImpl;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = "/dust",produces = "application/json;charset=UTF-8")
public class DustController {
    @Autowired
    public DustServiceImpl dustServiceImpl;
    /**
     * 查询最新烟尘浓度
     *
     * @param
     * @return
     */
    @GetMapping(value = "/newDust", produces = "application/json;charset=UTF-8")
    public String selectNewFt(DustModel dustModel, HttpServletRequest request) {
        String devid = "3";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        dustModel.setDevid(Integer.parseInt(devid));

        List<DustModel> newDust = dustServiceImpl.selectNewInfoById(dustModel);
        for (DustModel d : newDust
        ) {
            Date dt = d.getDcollectdt();
            d.setDcollectdt_re(DateUtil.dateToString(dt));
        }
        return JSON.toJSONString(newDust);
    }


    /**
     * 查询一天的浓度
     *
     * @param dustModel
     * @return
     */
    @GetMapping("/monitordayDust")
    public String getDayFt(DustModel dustModel, HttpServletRequest request) {
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
        double[] fDustArr = new double[24];
        String[] arrhours = new String[24];// {"","","","","","","","","","","","","","","","","","","","","","","",""};
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                arrhours[i] = "0" + String.valueOf(i) + ":00";
            } else {
                arrhours[i] = String.valueOf(i) + ":00";
            }
        }
        int pos = 0;
        dustModel.setDevid(Integer.parseInt(devid));
        dustModel.setDcollectstart(startdate);
        dustModel.setDcollectend(enddate);

        List<DustModel> newDust = dustServiceImpl.selectInfoByHour(dustModel);
        ResultData resultData = new ResultData();
        for (DustModel item : newDust) {
            Date dt = item.getDcollectdt();
            arrhours[pos] = dfhour.format(dt);
            if (item.getFdust() != null && pos < 24) {
                fDustArr[pos] = item.getFdust();
            }
            pos++;
        }
        resultData.setArrddata1(fDustArr);
        return JSON.toJSONString(resultData);
    }

}
