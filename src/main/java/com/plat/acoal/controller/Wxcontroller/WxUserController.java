package com.plat.acoal.controller.Wxcontroller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.model.CustomerInfo;
import com.plat.acoal.service.impl.CustomerServiceImpl;
import com.plat.acoal.utils.JsonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/wxuser",produces = "application/json;charset=UTF-8")
@Log4j2
public class WxUserController {
    @Autowired
    private CustomerServiceImpl customerServiceImpl;
  /*  *//**
     * 用户登录
     * @param username
     * @param password
     * @return
     *//*
    @PostMapping("/login")
    private JsonResult login(String username, String password, HttpServletRequest request){
        JsonResult jr = usi.selectUserByUserName(username,password);
        if (Integer.valueOf(200).equals(jr.getStatus())){
            request.getSession().setAttribute("user",jr.getData());
        }
        return jr;
    }*/

    @RequestMapping(value = "/customerinfo")
    private String login(@RequestParam Map<String,String> condition){
        List<CustomerInfo> list=new ArrayList<>();

        list=customerServiceImpl.selectCustomerByCondition(condition);

        return JSON.toJSONString(list);
    }
}
