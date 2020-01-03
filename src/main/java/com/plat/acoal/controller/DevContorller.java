package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.*;
import com.plat.acoal.model.AlarmInfo;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.DevInfoModel;
import com.plat.acoal.model.RegionModel;
import com.plat.acoal.service.RegionService;
import com.plat.acoal.service.impl.AlarmServiceImpl;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.RegionServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping(value = "/dev", produces = "application/json;charset=UTF-8")
public class DevContorller {
    @Autowired
    private DevServiceImpl dsi;
    @Autowired
    private AlarmServiceImpl alarmServiceImpl;
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    private RegionServiceImpl regionServiceImpl;


    @GetMapping("/dim")
    public String selectDevInfoModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }
        return JSON.toJSONString(dsi.selectDevInfoModelByCondtition(currentPage, condition));
    }

    @GetMapping("/name")
    public String selectNameByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        return JSON.toJSONString(dsi.selectNameByCondition(condition));
    }

    @GetMapping("/type")
    public String selectTypeByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerid = user.getIcustomerid();
        condition.put("customer",customer);
        */
        return JSON.toJSONString(dsi.selectTypeByCondition(condition));
    }

    @GetMapping("/dam")
    public String selectDevActiveModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }
        return JSON.toJSONString(dsi.selectDevActiveModelByCondtition(currentPage, condition));
    }

    ;

    /**
     * 查询消防炮状态列表
     */

    @GetMapping("/fire")
    public String getFireList(Dev dev, HttpServletRequest request) {
//
//        String devid = "3";
//        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
//            devid = request.getParameter("devid");
//        }
        //查询消防炮列表监测数据
        List<DevInfo> lst = dsi.selectFireList(dev);

        //查询对应的消防炮的报警状态
        AlarmInfo alarmInfo = new AlarmInfo();
        for (DevInfo item : lst
        ) {
            alarmInfo.setDevid(item.getId());
            List<AlarmInfo> lsta = alarmServiceImpl.selectAlarmInfoByCondition(alarmInfo);
            if (lsta.size() > 0) {
                item.setAlarm(1);
                item.setUpdateTime(lsta.get(0).getAlarmTime());
            }
        }
        return JSON.toJSONString(lst);
    }

    /**
     * 查询消防炮状态
     */
    @GetMapping("/nowFire")
    public String getFireStatus(DevInfo devInfo, HttpServletRequest request) {

        String devid = "8";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        devInfo.setId(Integer.parseInt(devid));
        //查询消防炮监测数据
        DevInfo devInfo1 = new DevInfo();
        devInfo1 = dsi.selectFireDev(devInfo);
        if (devInfo != null) {
            //查询对应的消防炮的报警状态
            AlarmInfo alarmInfo = new AlarmInfo();

            alarmInfo.setDevid(devInfo.getId());
            List<AlarmInfo> lsta = alarmServiceImpl.selectAlarmInfoByCondition(alarmInfo);
            if (lsta.size() > 0) {
                devInfo1.setAlarm(1);
                devInfo1.setUpdateTime(lsta.get(0).getAlarmTime());
            }
        }
        return JSON.toJSONString(devInfo1);
    }

    /**
     * 获取设备基础信息
     *
     * @param dev
     * @param request
     * @return
     */
    @GetMapping("/newstatus")
    public String getStatus(Dev dev, HttpServletRequest request) {
        String devid = "3";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }
        dev.setId(Integer.parseInt(devid));

        Dev newDev = devServiceImpl.selectDevById(dev);
        //获取该类设备总数
        int sum = 0;
        if (newDev != null) {
            int type = newDev.getType();
            sum = devServiceImpl.selectCountByType(type);
        }
        ResultData resultData = new ResultData();
        resultData.setDev(dev);
        resultData.setCount(sum);

        return JSON.toJSONString(resultData);
    }

    /**
     * 获取监测点列表
     */
    @GetMapping("/devlist")
    private String getdevlist(@RequestParam Map<String, String> condition, HttpSession session) {

       /* User user = (User)session.getAttribute("user");
        Integer icustomerid = user.getIcustomerid();*/
        Integer customerId = 2;


        Region region = new Region();
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }
        Integer regionId = 1;

        List<RegionModel> listrg = regionServiceImpl.selectRegionByCus(customerId);
        List<RegionModel> listrg_re = new ArrayList<RegionModel>();
//        List<Dev> listdev=new ArrayList<Dev>();

        for (RegionModel item : listrg
        ) {
            Dev dev = new Dev();
            if (item != null) {
                dev.setIcustomerid(customerId);
                dev.setRegion(item.getId());
            }
            List<Dev> listdev = devServiceImpl.selectDevByRegion(currentPage, dev);

            item.setDevs(listdev);
            listrg_re.add(item);
        }

        return JSON.toJSONString(listrg_re, SerializerFeature.DisableCircularReferenceDetect);
    }

}
