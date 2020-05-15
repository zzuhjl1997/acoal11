package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.OperationIAO;
import com.plat.acoal.service.OperationLogService;
import com.plat.acoal.service.impl.OperationLogServiceImpl;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping(value = "/operationLog",produces = "application/json;Charset=UTF-8")
public class OperationLogController {
    @Autowired
    public OperationLogServiceImpl osi;

    @RequestMapping("")
    public String selectLogs(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        String startdate = null;
        String enddate = null;
        Integer pageSize = 1;
        Integer currentPage = 1;
        Integer sequence = 0;
        Integer count = 0;

        Map<String, String> param = new HashMap<String, String>();
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.valueOf(condition.get("currentPage"));
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? 1 : Integer.valueOf(condition.get("pageSize"));

            condition.remove("currentPage");
            condition.remove("pageSize");
        } else {
            currentPage = null;
            pageSize = null;

        }
        if(condition.containsKey("startdate")&&condition.containsKey("enddate")){
            startdate = StringUtils.isBlank(condition.get("startdate")) ? null : String.valueOf(condition.get("startdate") + " 00:00:00");
            enddate = StringUtils.isBlank(condition.get("enddate")) ? null : String.valueOf(condition.get("enddate") + " 23:59:59");
        }else {
            startdate = null;
            enddate = null;
        }
        condition.put("startdate",startdate);
        condition.put("enddate",enddate);
        count = osi.selectLogsCount(condition);
        List<OperationIAO> list = osi.selectLogs(condition, currentPage, pageSize);
        List<OperationIAO> list_re = new ArrayList<OperationIAO>();
        for (OperationIAO item : list) {
            sequence++;
            item.setCount(sequence);
            item.setDate_re(DateUtil.dateToString(item.getOperationdate(),"yyyy-MM-dd HH:mm:ss"));
            list_re.add(item);
        }
        ResultData resultData = new ResultData();
        resultData.setData(list_re);
        resultData.setPagecount(count);
        return JSON.toJSONString(resultData);
    }
}
