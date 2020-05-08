package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.MstonLine;
import com.plat.acoal.service.impl.MstonLineServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @RequestMapping("/gastype")
    private String getgastype(){
        List<Map<String,String>> listgtype=new ArrayList<Map<String, String>>();
        Map<String,String> parama=new HashMap<String, String>();
        Map<String,String> parama2=new HashMap<String, String>();
        parama.put("id","5");
        parama.put("type","CO");
        parama2.put("id","6");
        parama2.put("type","CH4");
        listgtype.add(parama);
        listgtype.add(parama2);
        return JSON.toJSONString(listgtype);
    }
    @RequestMapping("/isauto")
    private String getauto(){
        List<Map<String,String>> listauto=new ArrayList<Map<String, String>>();
        Map<String,String> parama=new HashMap<String, String>();
        Map<String,String> parama2=new HashMap<String, String>();
        parama.put("code","0");
        parama.put("type","关闭");
        parama2.put("code","1");
        parama2.put("type","开启");
        listauto.add(parama);
        listauto.add(parama2);
        return JSON.toJSONString(listauto);
    }
   /* @RequestMapping("/status")
    private String getstatus(){
        List<Map<String,String>> listauto=new ArrayList<Map<String, String>>();
        Map<String,String> parama=new HashMap<String, String>();
        Map<String,String> parama2=new HashMap<String, String>();
        parama.put("code","0");
        parama.put("status","关闭");
        parama2.put("code","1");
        parama2.put("status","开启");
        parama2.put("status","开启");
        listauto.add(parama);
        listauto.add(parama2);
        return JSON.toJSONString(listauto);
    }*/
}
