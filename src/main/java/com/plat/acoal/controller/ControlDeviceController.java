package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.Dev;
import com.plat.acoal.entity.Region;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.RegionModel;
import com.plat.acoal.service.ControlDeviceService;
import com.plat.acoal.utils.JsonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/control")
public class ControlDeviceController {

    @Autowired
    private ControlDeviceService controlDeviceService;
    /**
     * 根据客户id查找风机设备
     * @return
     */
    @GetMapping("/fanlist")
    private String selectFanDevByCustomerId() {

//        User user = (User) session.getAttribute("user");
//        Integer icustomerid = user.getIcustomerid();
        Integer icustomerid = 2;

        List<RegionModel> regionList = controlDeviceService.selectFanDevByCustomerId(icustomerid);

        return JSON.toJSONString(regionList);
    }

    /**
     * 启动关闭风机设备
     * @return
     */
    private String updateFanDevById(String ids, Dev dev){
        JsonResult jr = new JsonResult();
        try {

            controlDeviceService.updateFanDevById(ids,dev);

            jr.setStatus(200);
            jr.setMsg("操作成功");
        } catch (Exception e) {
            e.printStackTrace();
            jr.setStatus(300);
            jr.setMsg("操作失败");
        }

        return JSON.toJSONString(jr);
    }

}
