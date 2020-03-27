package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.MstonLine;
import com.plat.acoal.service.impl.MstonLineServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(value = "/mstonline",produces = "application/json;CharSet=UTF-8")
public class MstonLineController {
    @Autowired
    public MstonLineServiceImpl mstonLineServiceImpl;
    @RequestMapping(value = "")
    private String getmstonline(){
        List<MstonLine>  list=mstonLineServiceImpl.selectAllOnline();

        return JSON.toJSONString(list);
    }
}
