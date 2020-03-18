package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.model.CannonInfo;
import com.plat.acoal.model.DevInfo;
import com.plat.acoal.model.TemperatureInfo;
import com.plat.acoal.service.impl.CannonServiceImpl;
import com.plat.acoal.utils.DateUtil;
import lombok.extern.log4j.Log4j2;
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

@RestController
@Log4j2
@RequestMapping(value = "/cannon", produces = "application/json;charset=UTF-8")
public class CannonController {
    @Autowired
    private CannonServiceImpl cannonServiceImpl;

    /**
     * 查询消防炮实时数据列表
     *
     * @param condition
     * @param session
     * @return
     */
    @GetMapping("/cannonlist")
    public String Cannonlist(@RequestParam Map<String, String> condition, HttpSession session) {
        Integer icustomerid = null;
        if (session.getAttribute("icustomerid") != null && !"".equals(session.getAttribute("icustomerid"))) {
            icustomerid = Integer.parseInt(session.getAttribute("icustomerid").toString());
        }
        condition.put("type","3");
        List<DevInfo> lstinfo = cannonServiceImpl.selectCannonList(condition);
        int count=0;
        for (DevInfo item:lstinfo
        ) {
            count ++;
            item.setCount(count);
            item.setLastTime(DateUtil.dateToString(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            System.out.println("更新时间"+DateUtil.dateToString(item.getUpdateTime(),"yyyy-MM-dd HH:mm:ss"));
            System.out.println("对象"+item);
        }
        return JSON.toJSONString(lstinfo);
    }
    /**
     * 查看消防炮实时数据
     */
    @GetMapping("/newcannon")
    public String getnewCannon(@RequestParam Map<String, String> condition, HttpSession session){
        List<TemperatureInfo> lstcannon = new ArrayList<TemperatureInfo>();
        List<CannonInfo> newcannon = cannonServiceImpl.selectNewCannonById(condition);
        String[] time={};
        int pos=0;
        ResultData resultData=new ResultData();
        for (CannonInfo c:newcannon
        ) {
            Date dt=c.getCollectdt();
            c.setLastTime(DateUtil.dateToString(dt));
        }
        return JSON.toJSONString(newcannon);

    }
}
