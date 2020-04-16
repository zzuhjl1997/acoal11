package com.plat.acoal.controller.Wxcontroller;

import com.plat.acoal.model.DevInfo;
import com.plat.acoal.service.impl.DevServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping(value = "/monitor",produces = "application/json;charset=UTF-8")
public class MonitorContoller {
    @Autowired
    public DevServiceImpl devServiceImpl;
    /**
     * 设备监测
     * @param condition
     * @param session
     * @return
     */
    @RequestMapping(value = "/devlist")
    private String devlist(Map<String,String> condition, HttpSession session){
        List<DevInfo> list =devServiceImpl.selectDevList(condition);








        return null;
    }
}
