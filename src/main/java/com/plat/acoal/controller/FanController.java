package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.DevActive;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.model.DevActiveInfo;
import com.plat.acoal.model.DevActiveModel;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.service.impl.DevServiceImpl;
import com.plat.acoal.service.impl.OperationLogServiceImpl;
import com.plat.acoal.utils.DateUtil;
import com.plat.acoal.utils.JsonResult;
import com.plat.acoal.utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Log4j2
@RequestMapping(value = "/fan", produces = "application/json;charset=UTF-8")
public class FanController {
    @Autowired
    public DevServiceImpl devServiceImpl;
    @Autowired
    private OperationLogServiceImpl operationLogService;
    @Autowired
    private DevServiceImpl getDevServiceImpl;

    @RequestMapping(value = "/faninfo")
    private String newfan(@RequestParam Map<String, String> condition, HttpSession session) {
        Integer currentPage = 1;
        Integer pageSize = 1;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? 1 : Integer.valueOf(condition.get("pageSize"));
            condition.remove("currentPage");
            condition.remove("pageSize");
        } else {
            currentPage = null;
        }

        //获取该设备的总运行时间
        String times = "";
        long time = 0;
        List<DevActiveModel> list_time = new ArrayList<DevActiveModel>();
        list_time = devServiceImpl.selectDevActiveModelByCondtition(currentPage, pageSize, condition).getList();
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
            if (devInfo.getLastTime() != null) {
                devInfo.setLastTime(devInfo.getLastTime().substring(0, 19));

            }
        }
        ResultData resultData = new ResultData();

        if (list.size() > 0) {
            resultData.setCode(200);
            resultData.setMsg("成功获取数据");
        } else {
            resultData.setCode(100);
            resultData.setMsg("数据为空");
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
        condition.put("startdate", startdate);
        condition.put("enddate", enddate);
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

    @RequestMapping(value = "/updatefan")
    private JsonResult updatefan(@RequestParam Map<String, String> condition, HttpSession session, HttpServletRequest request) {
        JsonResult jr = new JsonResult();
        DevActiveModel devActiveModel = new DevActiveModel();
        devActiveModel.setUserId(JwtUtils.getUser(request).getIuserid());
        devActiveModel.setDevId(Integer.parseInt(condition.get("id")));
        if (condition.get("status") != null || condition.get("is_auto") != null) {
            jr = devServiceImpl.updatefan(condition);
            if (condition.containsKey("status")) {
                if (condition.get("status").equals("1")) {
                    devActiveModel.setActflg(1);
                    devActiveModel.setDevActiveOpenTime(new Date());
                } else if (condition.get("status").equals("0")) {
                    devActiveModel.setActflg(2);
                    devActiveModel.setDevActiveCloseTime(new Date());
                }
            }
            int i = devServiceImpl.insertActiveInfo(devActiveModel);

        } else if (condition.get("status") == null && condition.get("status") == null) {
            jr.setStatus(100);
            jr.setMsg("参数不足");
        }
        return jr;
    }
}
