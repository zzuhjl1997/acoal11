package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.bean.ResultData;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.entity.User;
import com.plat.acoal.entity.Welcome;
import com.plat.acoal.model.WelcomeInfo;
import com.plat.acoal.service.CustomerService;
import com.plat.acoal.service.impl.CustomerServiceImpl;
import com.plat.acoal.service.impl.WelcomeServiceImpl;
import com.plat.acoal.utils.JsonResult;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Log4j2
@RequestMapping(value = "/customer", produces = "application/json;charset=UTF-8")
public class CustomerController {
    @Autowired
    public CustomerServiceImpl csi;
    @Autowired
    public WelcomeServiceImpl wsi;

    @RequestMapping("")
    public String selectAllCustomer(Customer customer) {
        // User user=new User();
        // Customer customer=new Customer();
        //log.settaction(request.getmethod);
        //request.getRequestURI();
        List<Customer> list = csi.selectAllCustomers(customer);
        //在这@auwired   operationlog
        ResultData resultData = new ResultData();
        resultData.setData(list);
        return JSON.toJSONString(resultData);
    }

    @RequestMapping(value = "/getword")
    public String getWord(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        List<WelcomeInfo> list = wsi.selectwelcome(condition);

        return JSON.toJSONString(list);
    }


    @RequestMapping(value = "/addword")
    public String addword(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        JsonResult jr = new JsonResult();
        jr.setStatus(100);
        int i = wsi.addWord(condition);

        if (i > 0) {
            jr.setStatus(200);
            jr.setMsg("添加成功");
        }

        return JSON.toJSONString(jr);
    }

    @RequestMapping("/updateword")
    public String updateword(@RequestParam Map<String, String> condition, HttpServletRequest request) {
        JsonResult jr = new JsonResult();
        jr.setStatus(100);
        int i = wsi.updateword(condition);
        if (i > 0) {
            jr.setStatus(200);
            jr.setMsg("修改成功");
        }
        return JSON.toJSONString(jr);

    }

    @PostMapping("/deleteword")
    public String deleteWord(@RequestParam Map<String, String> condition, HttpServletRequest request) {

        int i = wsi.deleteWord(condition);
        JsonResult jr = new JsonResult();
        jr.setStatus(100);
        if (i > 0) {
            jr.setStatus(200);
            jr.setMsg("删除成功");
        }

    return JSON.toJSONString(jr);
}}
