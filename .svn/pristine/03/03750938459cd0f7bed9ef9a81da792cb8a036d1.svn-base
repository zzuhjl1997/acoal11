package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.RealCannonData;
import com.plat.acoal.service.impl.RealCannonDataServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = "/realcannondata", produces = "application/json;charset=UTF-8")
public class RealCannonDataController {
    @Autowired
    private RealCannonDataServiceImpl realCannonDataServiceImpl;

    /**
     * 查询消防炮实时数据
     * @param realCannonData
     * @param request
     * @return
     */
    @GetMapping("/monitordata")
    public  String getNowRealCannon(RealCannonData realCannonData, HttpServletRequest request){
      /*  String devid = "3";
        if (request.getParameter("devid") != null && !"".equals(request.getParameter("devid"))) {
            devid = request.getParameter("devid");
        }*/

       List<RealCannonData> lst=realCannonDataServiceImpl.selectNowRealCannon(realCannonData);


        return  JSON.toJSONString(lst);


    }




}
