package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.DevActive;
import com.plat.acoal.model.DevActiveInfo;
import com.plat.acoal.model.DevActiveModel;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Log4j2
@RequestMapping(value = "/fan", produces = "application/json;charset=UTF-8")
public class FanController {
    @Autowired
    public DevServiceImpl devServiceImpl;

    @RequestMapping(value = "/faninfo")
    private String newfan(@RequestParam Map<String, String> condition, HttpSession session) {
        Integer currentPage = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            condition.remove("currentPage");
        } else {
            currentPage = null;
        }

        //获取该设备的总运行时间
        String times = "";
        long time = 0;
        List<DevActiveModel> list_time = new ArrayList<DevActiveModel>();
        list_time = devServiceImpl.selectDevActiveModelByCondtition(currentPage, condition);
        for (DevActiveModel item : list_time) {
            time += item.getDevActiveCloseTime().getTime() - item.getDevActiveOpenTime().getTime();
        }
        times = DateUtil.millisecondToTime(time);
        System.out.println(times);
        //获取设备数据
        List<DevInfo> list = new ArrayList<DevInfo>();
        list = devServiceImpl.selectFanInfo(condition);
        for (DevInfo devInfo : list) {
            devInfo.setSumtime(times);
        }

        return JSON.toJSONString(list);
    }

    /**
     * 获取某天的风机启动次数
     *
     * @param condition
     * @param session
     * @return
     */
    @RequestMapping(value = "/dayfan")
    private String dayfan(@RequestParam Map<String, String> condition, HttpSession session) {
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
        int[] count = new int[24];
        String[] arrhours = new String[24];// {"","","","","","","","","","","","","","","","","","","","","","","",""};
        for (int i = 0; i < 24; i++) {
            if (i < 10) {
                arrhours[i] = "0" + String.valueOf(i) + ":00";
            } else {
                arrhours[i] = String.valueOf(i) + ":00";
            }
        }
        int pos = 0;
        //统计启动次数
        List<DevActiveInfo> list = new ArrayList<>();
        list = devServiceImpl.selectCountOpen(condition);
        for (DevActiveInfo devActiveInfo : list) {
            if (devActiveInfo.getTime() != null) {
                String dt = devActiveInfo.getTime();
                pos = Integer.parseInt(dt.substring(11, 13));
                if (devActiveInfo.getNum() != null && pos < 24) {
                    arrhours[pos] = dt.substring(11, 13) + ":00";
                    count[pos] = devActiveInfo.getNum();
                }
            }
//            pos++;
        }
        ResultData resultData = new ResultData();
        resultData.setArridata1(count);
        resultData.setArrsdata1(arrhours);
        return JSON.toJSONString(resultData);

    }

}
