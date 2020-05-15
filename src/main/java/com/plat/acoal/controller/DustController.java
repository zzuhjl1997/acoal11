package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.DustModel;
import com.plat.acoal.model.GasModel;
import com.plat.acoal.model.ParameterInfo;
import com.plat.acoal.service.DevService;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.DustServiceImpl;
import com.plat.acoal.service.impl.ParameterServiceImpl;
import com.plat.acoal.utils.DateUtil;
import com.plat.acoal.utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping(value = "/dust", produces = "application/json;charset=UTF-8")
public class DustController {
    @Autowired
    public DustServiceImpl dustServiceImpl;
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    public ParameterServiceImpl parameterServiceImpl;

    /**
     * 查询最新烟尘浓度
     *
     * @param
     * @return
     */
    @RequestMapping(value = "/newDust")
    public String selectNewFt(DustModel dustModel, HttpServletRequest request) {
        String devid =null;
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        if (devid != null) {
            dustModel.setDevid(Integer.parseInt(devid));
        }
        List<DustModel> newDust = dustServiceImpl.selectNewInfoById(dustModel);

        double[] newVal = new double[3];
        int pos = 0;
        for (DustModel d : newDust
        ) {
            if (d != null) {
                newVal[pos] = d.getFdust();
            } else {
                newVal[pos] = 0.0;
            }
            System.out.println("最新时间");
            pos++;
        }
//        newdate = selectLastOne(newDust);
        String newdate = null;
        if (newDust.size() > 0) {
            newdate = (DateUtil.dateToString(newDust.get(0).getDcollectdt()));
        }
        ResultData resultData = new ResultData();
        resultData.setDate(newdate);
        resultData.setArrddata1(newVal);
        return JSON.toJSONString(resultData);
    }

    public String selectLastOne(List<DustModel> list) {
        DustModel dustModel = new DustModel();
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
     * 查询一天的浓度
     *
     * @param dustModel
     * @return
     */
    @RequestMapping("/monitordayDust")
    public String getDayFt(DustModel dustModel, HttpServletRequest request, @RequestParam Map<String, String> condition) {
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
            if (item.getDcollectdt() != null) {
                Date dt = item.getDcollectdt();
                pos = Integer.parseInt(DateUtil.dateToString(dt, "HH").substring(11, 13));
                arrhours[pos] = DateUtil.dateToString(dt, "HH");
                if (item.getFdust() != null && pos < 24) {
                    fDustArr[pos] = item.getFdust();
                }
//                pos++;
            }
        }

        condition.put("devid", devid.toString());
        System.out.println("devid:" + condition.get("devid"));
        condition.put("cparam", "D");
        List<ParameterInfo> listp = new ArrayList<ParameterInfo>();
        List<ParameterInfo> listp_re = new ArrayList<ParameterInfo>();
        listp = parameterServiceImpl.selectParamInfoByCondition(condition);
        if (listp.size() > 0) {
            listp_re = listp;
        } else {
            condition.put("devid", "0");
            System.out.println("devid:" + condition.get("devid"));
            condition.put("cparam", "D");
            listp_re = parameterServiceImpl.selectParamInfoByCondition(condition);
        }
        resultData.setData(listp_re);
        resultData.setArrddata1(fDustArr);
        resultData.setArrsdatax1(arrhours);
        return JSON.toJSONString(resultData);
    }

    /**
     * 烟尘监控列表
     *
     * @param devInfo
     * @param request
     * @return
     */
    @RequestMapping("/dustList")
    public String getCoList(DevInfo devInfo, HttpServletRequest request, @RequestParam Map<String, String> condition) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        Integer currentPage = 1;
        Integer pageSize = 1;

        if (condition.containsKey("currentPage")) {
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
        devInfo.setType(4);
        condition.put("type", "4");
        //查询烟尘设备数
        int devcount = 0;
        condition.remove("online");
        devcount = devServiceImpl.selectCountByType(condition);
        //查询烟尘的条数
        int count = 0;
        count = dustServiceImpl.selectDustCount(condition);
        List<DevInfo> listinfo = dustServiceImpl.selectDustList(devInfo, currentPage, pageSize);
        int sequence = 0;
        for (DevInfo item : listinfo
        ) {
            sequence++;
            item.setCount(sequence);
            item.setLastTime(DateUtil.dateToString(item.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));

        }
        ResultData resultData = new ResultData();
        resultData.setPagecount(count);
        resultData.setDevcount(devcount);
        resultData.setData(listinfo);
        return JSON.toJSONString(resultData);
    }
}
