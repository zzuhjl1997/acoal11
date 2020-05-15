package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Parameter;
import com.plat.acoal.entity.Temperature;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.ParameterInfo;
import com.plat.acoal.model.TemperatureInfo;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.ParameterServiceImpl;
import com.plat.acoal.service.impl.TemperatureServiceImpl;
import com.plat.acoal.utils.DateUtil;
import com.plat.acoal.utils.JwtUtils;
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
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    public ParameterServiceImpl parameterServiceImpl;

    /**
     * 查询最新温度
     *
     * @param
     * @return
     */
    @RequestMapping("/newFt")
    public String selectNewFt(TemperatureInfo temperatureInfo, HttpServletRequest request) {
        String devid = null;
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        double[] newVal = new double[1];
        temperatureInfo.setDevid(Integer.parseInt(devid));
        List<TemperatureInfo> lstft = new ArrayList<TemperatureInfo>();
        List<TemperatureInfo> newFt = temperatureServiceImpl.selectNewFtById(temperatureInfo);
        String[] time = {};
        int pos = 0;
        ResultData resultData = new ResultData();
        for (TemperatureInfo t : newFt
        ) {
//            Date dt = t.getDcollectdt();

            if (t != null) {
                newVal[pos] = t.getFt();
            } else {
                newVal[pos] = 0.0;
            }
            pos++;
        }
//        TemperatureInfo t1 = new TemperatureInfo();
        String newdate = null;
        if (newFt.size() > 0) {
            newdate = DateUtil.dateToString(newFt.get(0).getDcollectdt());
        }

        resultData.setDate(newdate);
        resultData.setArrddata1(newVal);
        return JSON.toJSONString(resultData);
    }


    public String selectLastOne(List<TemperatureInfo> list) {
        TemperatureInfo temperatureInfo = new TemperatureInfo();
        String newdate = null;
        Long dates[] = new Long[list.size()];
        for (int i = 0; i < list.size(); i++) {
            // 把date类型的时间对象转换为long类型，时间越往后，long的值就越大，
            // 所以就依靠这个原理来判断距离现在最近的时间
            dates[i] = list.get(i).getDcollectdt().getTime();
        }
        Long maxIndex = dates[0];// 定义最大值为该数组的第一个数
        for (int j = 0; j < dates.length; j++) {
            if (maxIndex < dates[j]) {
                maxIndex = dates[j];
                // 找到了这个j
                newdate = (DateUtil.dateToString(list.get(j).getDcollectdt()));
            }
        }
        return newdate;
    }

    /**
     * 查询某天的温度
     *
     * @param temperatureInfo
     * @return
     */
    @RequestMapping("/monitordayFt")
    public String getDayFt(TemperatureInfo temperatureInfo, HttpServletRequest request, @RequestParam Map<String, String> condition) {
        String devid = null;
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        Date date1 = new Date();
        String startdate = null;
        String enddate = null;
        if (condition.containsKey("date")) {
            startdate = StringUtils.isBlank(condition.get("date")) ? null : String.valueOf(condition.get("date") + " 00:00:00");
            enddate = StringUtils.isBlank(condition.get("date")) ? null : String.valueOf(condition.get("date") + " 23:59:59");
        } else {
            startdate = DateUtil.dateToString(date1, "yyyy-MM-dd") + " 00:00:00";
            enddate = DateUtil.dateToString(date1, "yyyy-MM-dd") + " 23:59:59";
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

        List<Temperature> newFt = temperatureServiceImpl.selectFtByHour(temperatureInfo);
        for (Temperature item : newFt) {
            if (item.getDcollectdt() != null) {
                Date dt = item.getDcollectdt();
                pos = Integer.parseInt(DateUtil.dateToString(dt).substring(11, 13));
                arrhours[pos] = DateUtil.dateToString(dt, "HH");
                if (item.getFt() != null && pos < 24) {
                    ftArr[pos] = item.getFt();
                    arrhours[pos] = DateUtil.dateToString(dt, "HH:mm");
                }
            }
//            pos++;
        }
//        ParameterInfo parameterInfo = new ParameterInfo();
        condition.put("devid", devid.toString());
        System.out.println("devid:" + condition.get("devid"));
        condition.put("cparam", "T");
        List<ParameterInfo> listp = new ArrayList<ParameterInfo>();
        List<ParameterInfo> listp_re = new ArrayList<ParameterInfo>();
        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
        if (listp.size() > 0) {
            listp_re = listp;
        } else {
            condition.put("devid", "0");
            System.out.println("devid:" + condition.get("devid"));
            condition.put("cparam", "T");
            listp_re = parameterServiceImpl.selectParamInfoByCondition(condition);
        }


        ResultData resultData = new ResultData();
        resultData.setData(listp_re);
        resultData.setArrddata1(ftArr);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);
    }

    /**
     * 温度监控列表
     *
     * @param devInfo
     * @param request
     * @return
     */
    @RequestMapping("/ftList")
    public String getCoList(DevInfo devInfo, HttpServletRequest request, HttpSession session, @RequestParam Map<String, String> condition) throws ParseException {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
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
            pageSize = null;
        }
        String devname = null;
        if (devname != null) {
            devInfo.setName(devname);
        }
        if (condition.containsKey("devname")) {

            devname = StringUtils.isBlank(condition.get("devname")) ? null : condition.get("devname");
        }
        Map<String, String> param = new HashMap<String, String>();

        devInfo.setType(2);
        devInfo.setIcustomerid(icustomerid);

        condition.put("type", "2");
        condition.put("icustomerid", "2");

        //查询设备总数
        int devcount = devServiceImpl.selectCountByType(condition);
        List<DevInfo> listinfo = temperatureServiceImpl.selectFtList(devInfo, currentPage, pageSize);
        int sequence = 0;
        //获取数据总条数
        int count = 0;
        count = temperatureServiceImpl.selectFtCount(condition);
        for (DevInfo item : listinfo) {
            sequence++;
            item.setCount(sequence);
            item.setLastTime(DateUtil.dateToString(item.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));

            System.out.println("更新时间" + DateUtil.dateToString(item.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            System.out.println("更新时间" + item.getUpdateTime());

        }
        ResultData resultData = new ResultData();
        resultData.setPagecount(count);
        resultData.setData(listinfo);
        resultData.setDevcount(devcount);
        return JSON.toJSONString(resultData);
    }
}