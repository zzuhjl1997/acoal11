package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.CannonInfo;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.TemperatureInfo;
import com.plat.acoal.service.impl.CannonServiceImpl;
import com.plat.acoal.service.impl.DevServiceImpl;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@Log4j2
@RequestMapping(value = "/cannon", produces = "application/json;charset=UTF-8")
public class CannonController {
    @Autowired
    private CannonServiceImpl cannonServiceImpl;
    @Autowired
    private DevServiceImpl devServiceImpl;

    /**
     * 查询消防炮实时数据列表
     *
     * @param condition
     * @param request
     * @return
     */
    @RequestMapping("/cannonlist")
    public String Cannonlist(@RequestParam Map<String, String> condition, HttpServletRequest request) {

        if (condition.containsKey("status")) {
            if (condition.get("status").equals("1")) {
                condition.put("isopen", "1");
            } else if (condition.get("status").equals("0")) {
                condition.put("isopen", "0");
            }
        }


        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        Integer currentPage = 1;
        Integer pageSize = 1;
        //1=火警 2=故障 3=正常 4=离线
        if (condition.containsKey("cstatus") && condition.get("cstatus") != null) {
            int canno_status = Integer.parseInt(condition.get("cstatus"));
            switch (canno_status) {
                case 1:
                    condition.put("online", "1");
                    condition.put("isfire", "1");
                    break;
                case 2:
                    condition.put("online", "1");
                    condition.put("isfault", "1");
                    break;
                case 3:
                    condition.put("online", "1");
                    condition.put("isfault", "0");
                    condition.put("isfire", "0");
                    break;
                case 4:
                    condition.put("online", "0");
                    condition.put("isfault", "0");
                    break;
            }
        }
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
            condition.put("name", devname);
        }
        if (icustomerid != null) {
            condition.put("icustomerid", icustomerid.toString());
        }
        condition.put("type", "3");
        int devcount = 0;
        devcount = devServiceImpl.selectCountByType(condition);
        List<DevInfo> lstinfo = cannonServiceImpl.selectCannonList(condition, currentPage, pageSize);
        List<DevInfo> list_re = new ArrayList<>();
        int count = 0;
        count = cannonServiceImpl.selectCannonCount(condition);
        int sequence = 0;
        for (DevInfo item : lstinfo
        ) {
            sequence++;
            item.setCount(sequence);
            item.setLastTime(DateUtil.dateToString(item.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            System.out.println("更新时间" + DateUtil.dateToString(item.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
            System.out.println("对象" + item);
            //1=火警 2=故障 3=正常 4=离线
            if (item.getOnline() == 1) {
                if (item.getIsfire() == 1) {
                    item.setCanno_status(1);
                } else if (item.getIsfault() == 1) {
                    item.setCanno_status(2);
                } else {
                    item.setCanno_status(3);
                }
            } else if (item.getOnline() == 0) {
                item.setCanno_status(4);
            }


        }
    /*    if (condition.containsKey("cstatus") && condition.get("cstatus") != null) {
            int canno_status = Integer.parseInt(condition.get("cstatus"));
            switch (canno_status) {
                case 1:
                    list_re=lstinfo.stream().filter(devInfo -> devInfo.getCanno_status()!=null&&devInfo.getCanno_status().equals(1)).collect(Collectors.toList());
                    break;
                case 2:
                    list_re=lstinfo.stream().filter(devInfo -> devInfo.getCanno_status()!=null&&devInfo.getCanno_status().equals(2)).collect(Collectors.toList());
                    break;
                case 3:
                    list_re=lstinfo.stream().filter(devInfo -> devInfo.getCanno_status()!=null&&devInfo.getCanno_status().equals(3)).collect(Collectors.toList());
                    break;
                case 4:
                    list_re=lstinfo.stream().filter(devInfo -> devInfo.getCanno_status()!=null&&devInfo.getCanno_status().equals(4)).collect(Collectors.toList());
                    break;
            }
        }*/

        ResultData resultData = new ResultData();
        resultData.setPagecount(count);
        resultData.setData(lstinfo);
        resultData.setDevcount(devcount);
        return JSON.toJSONString(resultData);
    }

    /**
     * 查看消防炮实时数据
     */
    @GetMapping("/newcannon")
    public String getnewCannon(@RequestParam Map<String, String> condition, HttpSession session) {
        List<TemperatureInfo> lstcannon = new ArrayList<TemperatureInfo>();
        List<CannonInfo> newcannon = cannonServiceImpl.selectNewCannonById(condition);
        String[] time = {};
        int pos = 0;
        ResultData resultData = new ResultData();
        for (CannonInfo c : newcannon
        ) {
            Date dt = c.getCollectdt();
            c.setLastTime(DateUtil.dateToString(dt));
        }
        return JSON.toJSONString(newcannon);

    }
}
