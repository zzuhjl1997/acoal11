package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.OperationIAO;
import com.plat.acoal.service.OperationLogService;
import com.plat.acoal.service.impl.OperationLogServiceImpl;
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
@RequestMapping("/operationLog")
public class OperationLogController {
    @Autowired
    public OperationLogServiceImpl osi;

    @RequestMapping("")
    public String selectLogs(OperationIAO operationIAO, @RequestParam Map<String, String> condition, HttpSession session) {
        Integer icustomerid = null;
        if (session.getAttribute("icustomerid") != null && !"".equals(session.getAttribute("icustomerid"))) {
            icustomerid = Integer.parseInt(session.getAttribute("icustomerid").toString());
        }
        Integer currentPage = 1;
        Integer pageSize = 1;
        Integer sequence = 0;
//        Integer tatal=0;
        String username=null;
        if(username!=null){
           operationIAO.setUsername(username);
        }
        Map<String, String> param = new HashMap<String, String>();
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
        List<OperationIAO> list = osi.selectLogs(operationIAO, currentPage, pageSize);
        List<OperationIAO> list_re = new ArrayList<OperationIAO>();
        for (OperationIAO item : list) {
            sequence++;
            item.setCount(sequence);
            list_re.add(item);
        }
//        param.put("tatal", String.valueOf(sequence));
        ResultData resultData = new ResultData();
        resultData.setData(list_re);
        resultData.setPagecount(sequence);
        return JSON.toJSONString(resultData);
    }
}
