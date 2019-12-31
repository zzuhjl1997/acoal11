package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.service.impl.AlarmServiceImpl;
import com.plat.acoal.utils.JsonResult;
import com.plat.acoal.utils.PoiUtils;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/alarm", produces = "application/json;charset=UTF-8")
@Log4j2
public class AlarmController {
    @Autowired
    AlarmServiceImpl atsi;

    @GetMapping("/am")
    public String selectAlarmModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
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
        return JSON.toJSONString(atsi.selectAlarmModelByCondition(currentPage, condition));
    }

    @DeleteMapping("/am")
    public String deleteByAlarmId(@RequestParam(value = "alarmIds") String alarmIds) {
        JsonResult jr = new JsonResult();
        String[] alarmArray = alarmIds.split(",");
        int iserror = 0;
        for (int i = 0; i < alarmArray.length; i++) {
            iserror += atsi.deleteByPrimaryKey(Long.valueOf(alarmArray[i]));
        }
        if (iserror == alarmArray.length) {
            jr.setMsg("删除完毕！");
            jr.setStatus(200);
        } else if (iserror < alarmArray.length) {
            jr.setMsg("删除错误！");
            jr.setStatus(500);
        } else if (iserror > alarmArray.length) {
            jr.setStatus(555);
            jr.setMsg("删除异常！可能出现删除多个记录的情况！");
        }
        return JSON.toJSONString(jr);
    }

    @GetMapping("/export")
    public ResponseEntity<byte[]> exportAlarmModelByCondition(@RequestParam Map<String, String> condition,HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        return PoiUtils.exportAlarmModel2Excel(atsi.selectAlarmModelByCondition(null,condition));
    }
}
