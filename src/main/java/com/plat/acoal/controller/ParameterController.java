package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.entity.Parameter;
import com.plat.acoal.entity.User;
import com.plat.acoal.service.OperationLogService;
import com.plat.acoal.service.ParameterService;
import com.plat.acoal.service.impl.ParameterServiceImpl;
import com.plat.acoal.utils.JsonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@Log4j2
@RequestMapping(value = "/param", produces = "application/json;Charset=UTF-8")
public class ParameterController {
    @Autowired
    private ParameterServiceImpl parameterService;
    @Autowired
    private OperationLogService operationLogService;

    /**
     * 添加报警参数
     */
    @PostMapping("/add")
    private String addParameter(Parameter parameter) {
        int res = parameterService.addParameter(parameter);
        JsonResult jr = new JsonResult();
        if (res > 0) {
            jr.setStatus(200);
            jr.setMsg("添加成功");
        }
        return JSON.toJSONString(jr);
    }

    /**
     * 根据父参数查看报警参数信息
     */
    @PostMapping("/list")
    private String selectParamByCondition(String cparam, @RequestParam(value = "devId", required = false, defaultValue = "0") Integer devId, HttpServletRequest request, HttpSession session) {
        // 获取customerid
        Integer icustomerid = 2;
//        HttpSession session = request.getSession();
        if (session.getAttribute("user") != null) {
            User user = (User) session.getAttribute("user");
            icustomerid = user.getIcustomerid();
        }
        List<Parameter> list = parameterService.selectParamByCondition(cparam, icustomerid, devId);
        return JSON.toJSONString(list);
    }

    /**
     * 修改参数
     */
    @PostMapping("/update")
    private String updateParameter(Parameter parameter, Integer ischecked, HttpServletRequest request) {
        // 获取customerid
        //Optional.ofNullable()
        Integer icustomerid = 2;
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Integer userid=null;
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
