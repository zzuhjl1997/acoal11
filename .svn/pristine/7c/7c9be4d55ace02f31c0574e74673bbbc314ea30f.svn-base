package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.service.RegionService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;

@RestController
@RequestMapping(value = "/region", produces = "application/json;charset=UTF-8")
@Log4j2
public class RegionController {
    @Autowired
    RegionService rs;
    @GetMapping("")
    public String selectRegionModelByCondition(@RequestParam Map<String, String> condition, HttpServletRequest res) {
        return JSON.toJSONString(rs.selectRegionModelByCondition(condition));
    }
}
