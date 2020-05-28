package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.dao.DevMapper;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.entity.Parameter;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.ParameterInfo;
import com.plat.acoal.service.OperationLogService;
import com.plat.acoal.service.ParameterService;
import com.plat.acoal.service.impl.OperationLogServiceImpl;
import com.plat.acoal.service.impl.ParameterServiceImpl;
import com.plat.acoal.utils.JsonResult;
import com.plat.acoal.utils.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@Log4j2
@RequestMapping(value = "/param", produces = "application/json;Charset=UTF-8")
public class ParameterController {
    @Autowired
    private ParameterServiceImpl parameterService;
    @Autowired
    private OperationLogServiceImpl operationLogService;
    @Autowired
    private DevMapper devMapper;

    /**
     * 添加报警参数
     */
   /* @PostMapping("/add")
    private String addParameter(Parameter parameter) {
        int res = parameterService.addParameter(parameter);
        JsonResult jr = new JsonResult();
        if (res > 0) {
            jr.setStatus(200);
            jr.setMsg("添加成功");
        }
        return JSON.toJSONString(jr);
    }*/

    /**
     * 根据父参数查看报警参数信息
     */

    @PostMapping("/list")
    private String selectParamByCondition(String cparam, @RequestParam(value = "devId", required = false, defaultValue = "0") Integer devId, HttpServletRequest request, HttpSession session) {
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        icustomerid = user.getIcustomerid();
        Map<String, String> condition = new HashMap<>();
        condition.put("devid", devId.toString());

        List<DevInfo> listd = new ArrayList<>();

        listd = devMapper.selectDevInfoByDevid(condition);

        if (listd.size() > 0) {
            for (DevInfo devInfo : listd) {
                int type = devInfo.getType();
                if (type == 5) {
                    cparam = "CO";
                } else if (type == 6) {
                    cparam = "CH4";
                } else if (type == 7) {
                    cparam = "P";
                } else if (type == 8) {
                    cparam = "F";
                }
            }
        }

        List<ParameterInfo> list = parameterService.selectParamByCondition(cparam, icustomerid, devId);

        if (list.size() < 1) {
            list = parameterService.selectParamByCondition(cparam, icustomerid, 0);
        }
        for (ParameterInfo parameterInfo : list) {
            parameterInfo.setDevId(devId);
        }
        return JSON.toJSONString(list);
    }

    /**
     * 修改参数
     */
    @PostMapping("/update")
    private String updateParameter(@RequestParam Map<String, String> condition,Parameter parameter, Integer ischecked, HttpServletRequest request) {
        // 获取customerid
        //Optional.ofNullable()
        Integer icustomerid = null;
        User user = JwtUtils.getUser(request);
        Integer userid = null;
        if (user != null) {
            icustomerid = user.getIcustomerid();
            parameter.setIcustomerid(icustomerid);
            userid = user.getIuserid();
        }

        JsonResult jr = new JsonResult();
        try {
            parameterService.updateParameter(parameter, ischecked);
            OperationLog ol = new OperationLog();
            /* userid=517704512;*/
            String uri = request.getRequestURI();
            String action = request.getMethod();
            ol.setOperationdate(new Date());
            ol.setOperationuserid(userid);
            ol.setTaction(action);
            ol.setTurl(uri);
            ol.setTurlname("修改参数");
            ol.setStatus(0);
            int i = operationLogService.addLogs(ol);
            if (i > 0) {
                jr.setStatus(200);
                jr.setMsg("修改成功");
            }
        } catch (Exception e) {
            jr.setStatus(300);
            jr.setMsg("修改失败");
            e.printStackTrace();
        }
        return JSON.toJSONString(jr);
    }
}
