package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.plat.acoal.service.impl.AlarmServiceImpl;
import com.plat.acoal.utils.DateUtil;
import com.plat.acoal.utils.JsonResult;
import com.plat.acoal.utils.JwtUtils;
import com.plat.acoal.utils.PoiUtils;
import io.jsonwebtoken.Claims;
import io.swagger.annotations.*;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Stream;

/*
 *所有方法命名格式为：操作+对象+条件
 *所有实体中的属性命名格式为：表名+字段名。字段在多表中存在时，主键为该字段的表优先写。
 */

@RestController
@Api(value = "报警Controller", tags = {"报警接口"})
@RequestMapping(value = "/alarm", produces = "application/json;charset=UTF-8")
@Log4j2
public class AlarmController {
    @Autowired
    AlarmServiceImpl atsi;

    @ApiOperation(value = "通过条件获取报警模型")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "regionId", value = "区域ID", dataType = "String"),
            @ApiImplicitParam(name = "", value = "", dataType = ""),
            @ApiImplicitParam(name = "", value = "", dataType = ""),
            @ApiImplicitParam(name = "", value = "", dataType = ""),
            @ApiImplicitParam(name = "", value = "", dataType = ""),
            @ApiImplicitParam(name = "", value = "", dataType = ""),
            @ApiImplicitParam(name = "", value = "", dataType = ""),
            @ApiImplicitParam(name = "", value = "", dataType = ""),
    })
    @GetMapping("/am")
    public String selectAlarmModelByCondition(@RequestParam Map<String, String> condition, HttpServletRequest req) {
        System.out.println("通过token获取到USER："+ JwtUtils.getUser(req));
        int currentPage = 1;
        int pageSize = Integer.MAX_VALUE;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.parseInt(condition.get("currentPage"));
            condition.remove("currentPage");
        }
        if (condition.containsKey("pageSize")) {
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? Integer.MAX_VALUE : Integer.parseInt(condition.get("pageSize"));
            condition.remove("pageSize");
        }
        return JSON.toJSONString(atsi.selectAlarmModelByCondition(currentPage, pageSize, condition));
    }

    @ApiOperation(value = "通过条件获取设备报警模型")
    @GetMapping("/dam")
    public String selectDevAlarmModelByCondition(@RequestParam Map<String, String> condition, HttpServletRequest req) {
        return JSON.toJSONString(atsi.selectDevAlarmModel(condition));
    }

    @ApiOperation(value = "通过多个报警的ID删除报警信息")
    @DeleteMapping("/am")
    public String deleteByAlarmId(@RequestParam(value = "alarmIds") String alarmIds) {
        JsonResult jr = new JsonResult();
        int num = alarmIds.split(",").length;
        int iserror = Stream.of(alarmIds)
                .map(a -> a.split(","))
                .flatMap(Arrays::stream)
                .map(Long::valueOf).mapToInt(atsi::deleteByPrimaryKey).sum();
        if (iserror == num) {
            jr.setMsg("删除完毕！");
            jr.setStatus(200);
        } else if (iserror < num) {
            jr.setMsg("删除错误！");
            jr.setStatus(500);
        } else {
            jr.setStatus(555);
            jr.setMsg("删除异常！可能出现删除多个记录的情况！");
        }
        return JSON.toJSONString(jr);
        /*
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
         */
    }

    @ApiOperation(value = "通过条件查询报警信息后导出")
    @GetMapping("/exp")
    public ResponseEntity<byte[]> exportAlarmModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        /*User user = (User)session.getAttribute("user");
        Integer customerId = user.getIcustomerid();
        condition.put("customerId",customerId.toString());*/
        List list = atsi.selectAlarmModelByCondition(null, Integer.MAX_VALUE, condition).getList();
        System.out.println(list);
        return PoiUtils.exportAlarmModel2Excel(atsi.selectAlarmModelByCondition(null, Integer.MAX_VALUE, condition).getList());
    }

    @ApiOperation(value = "通过条件获取报警统计模型", notes = "用于折线图")
    @GetMapping("/asm")
    public String selectAlarmStatisticsModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        String alarmTimeHead = "";
        String alarmTimeTail = "";
        if (condition.containsKey("alarmTimeHead")) {
            alarmTimeHead = condition.get("alarmTimeHead");
            if (!condition.containsKey("alarmTimeTail")) {
                alarmTimeTail = DateUtil.aFewDaysAgo(alarmTimeHead, 7, "yyyy-MM-dd");
            }
        }
        if (condition.containsKey("alarmTimeTail")) {
            alarmTimeTail = condition.get("alarmTimeTail");
            if (!condition.containsKey("alarmTimeHead")) {
                alarmTimeHead = DateUtil.aFewDaysAgo(alarmTimeTail, -7, "yyyy-MM-dd");
            }
        }
        if (!condition.containsKey("alarmTimeTail") && !condition.containsKey("alarmTimeHead")) {
            LocalDate ld = LocalDate.now();
            alarmTimeTail = ld.toString();
            alarmTimeHead = ld.minusDays(6).toString();
        }
        if (!"".equals(alarmTimeHead) && !"".equals(alarmTimeTail)) {
            condition.put("alarmTimeHead", alarmTimeHead);
            condition.put("alarmTimeTail", alarmTimeTail);
        }
        return JSON.toJSONString(atsi.selectAlarmStatisticsModelByCondition(condition));
    }

    @GetMapping("/atm")
    public String selectAlarmTypeModelByCondition() {
        return JSON.toJSONString(atsi.selectAlarmTypeModel());
    }

    @GetMapping("/agm")
    public String selectAlarmGradeModelByCondition() {
        return JSON.toJSONString(atsi.selectAlarmGradeModel());
    }

    @GetMapping("/arm")
    public String selectAlarmRatioModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(atsi.selectAlarmRatioModel());
    }

    @GetMapping("/dafm")
    public String selectDevAlarmFrequencyModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(atsi.selectDevAlarmFrequencyModel(condition));
    }

    @GetMapping("/daim")
    public String selectDevAlarmInfoModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        if("".equals(condition.get("devType"))){
            condition.remove("devType");
        }
        return JSON.toJSONString(atsi.selectDevAlarmInfoModel(condition));
    }

    @GetMapping("/dafcm")
    public String selectAlarmFourCountModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(atsi.selectAlarmFourCountModel());
    }

    @GetMapping("/taam")
    //获取当日的报警总数
    public String selectTodayAlarmAmountModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(atsi.selectTodayAlarmAmountModel(condition));
    }

    @GetMapping("/tuam")
    //获取当日的未处理的报警总数
    public String selectTodayUntreatedAlarmModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(atsi.selectTodayAlarmUntreatedModel(condition));
    }

    @GetMapping("/aavm")
    //获取所有的报警数
    public String selectAlarmAmountValueModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(atsi.selectAlarmAmountModel(condition));
    }

    @GetMapping("/uavm")
    //获取所有的未处理报警总数
    public String selectUntreatedAlarmValueModelByCondition(@RequestParam Map<String, String> condition, HttpSession session) {
        return JSON.toJSONString(atsi.selectAlarmUntreatedModel(condition));
    }

    @ApiOperation(value = "通过参数获取未处理的报警信息")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "pageSize", value = "页面大小"),
            @ApiImplicitParam(name = "currentPage", value = "当前页码数"),
            @ApiImplicitParam(name = "devName", value = "设备名称"),
            @ApiImplicitParam(name = "alarmGrade", value = "报警等级ID"),
            @ApiImplicitParam(name = "alarmType", value = "报警类型ID"),
            @ApiImplicitParam(name = "alarmTimeHead", value = "开始时间"),
            @ApiImplicitParam(name = "alarmTimeTail", value = "结束时间"),
    })
    @GetMapping("/uam")
    public String selectUntreatedAlarmModelByCondidition(@RequestParam Map<String, String> condition, HttpSession session) {
        int currentPage = 1;
        int pageSize = Integer.MAX_VALUE;
        if (condition.containsKey("currentPage")) {
            currentPage = StringUtils.isBlank(condition.get("currentPage")) ? 1 : Integer.parseInt(condition.get("currentPage"));
            condition.remove("currentPage");
        }
        if (condition.containsKey("pageSize")) {
            pageSize = StringUtils.isBlank(condition.get("pageSize")) ? Integer.MAX_VALUE : Integer.parseInt(condition.get("pageSize"));
            condition.remove("pageSize");
        }
        int finalPageSize = pageSize;
        Optional.of(currentPage).ifPresent(a -> PageHelper.startPage(a, finalPageSize));
        return JSON.toJSONString(new PageInfo<>(atsi.selectUntreatedAlarmModel(condition)));
    }

    @ApiOperation(value = "根据报警主键更新报警信息的处理状态")
    @PutMapping("/uam")
    public String updateUntreatedAlarmStatusByCondition(@RequestParam Map<String,String> condition, HttpSession session){
        return JSON.toJSONString(atsi.updateUntreatedAlarmStatus(condition));
    }
}
