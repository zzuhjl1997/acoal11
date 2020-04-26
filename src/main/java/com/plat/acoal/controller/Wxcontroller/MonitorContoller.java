package com.plat.acoal.controller.Wxcontroller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.service.impl.AlarmServiceImpl;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RestController
@Log4j2
@RequestMapping(value = "/monitor", produces = "application/json;charset=UTF-8")
public class MonitorContoller {
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    public AlarmServiceImpl alarmServiceImpl;

    /**
     * 设备监测
     *
     * @param condition
     * @param session
     * @return
     */
    @RequestMapping(value = "/devlist")
    private String devlist(@RequestParam Map<String, String> condition, HttpSession session) {
      /*  //正常 离线 报警
        if(condition.containsKey("status")){
            if(condition.get("status").equals("1")){
                if(condition.get("status").equals("2")){
                    condition.get("");
                }
                condition.put("online","1");
            }else {
                condition.put("online","0");
            }
        }*/
        List<DevInfo> list = devServiceImpl.selectDevList(condition);
        List<DevInfo> listr = new ArrayList<>();
        List<DevInfo> list_re = new ArrayList<>();
        //查询基础信息
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.MINUTE, -30);
        String c = cal.getTime().toString();
        System.out.println(cal.getTime());
        String starttime = DateUtil.dateToString(cal.getTime(), "yyyy-MM-dd HH:mm:ss");
        String endtime = DateUtil.dateToString(new Date(), "yyyy-MM-dd HH:mm:ss");
        int alacount = 0;
        condition.put("starttime", starttime);
        condition.put("endtime", endtime);
        for (DevInfo devInfo : list) {
            condition.put("devid", String.valueOf(devInfo.getId()));
            alacount = alarmServiceImpl.selectAlarmCount(condition);
            condition.remove("devid");
            //0=离线 1=正常 2=报警
            if (devInfo != null) {
                if (devInfo.getOnline() != null && devInfo.getOnline() == 1) {
                    if (alacount > 0) {
                        devInfo.setRemark("报警");
                    } else {
                        devInfo.setRemark("正常");
                    }
                } else {
                    devInfo.setRemark("离线");
                }
                listr.add(devInfo);
            }

        }

        //正常 离线 报警
        try {


            if (condition.containsKey("status")) {
                if (condition.get("status").equals(1)) {
                    if (condition.get("status").equals(2)) {
                        list_re = listr.stream().filter(devInfo -> devInfo.getRemark().equals("报警")).collect(Collectors.toList());
                    } else {
                        list_re = listr.stream().filter(devInfo -> devInfo.getOnline().equals(1)).collect(Collectors.toList());
                    }
                } else {
                    list_re = listr.stream().filter(devInfo -> devInfo.getOnline()==0).collect(Collectors.toList());
                }
            } else {
                list_re = listr;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return JSON.toJSONString(list_re);
    }
}
