package com.plat.acoal.controller;

import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.entity.OperationLog;
import com.plat.acoal.entity.User;
import com.plat.acoal.model.OperationIAO;
import com.plat.acoal.service.OperationLogService;
import com.plat.acoal.service.impl.OperationLogServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
@RestController
@Log4j2
@RequestMapping("/operationLog")
public class OperationLogController {
    @Autowired
    public OperationLogServiceImpl osi;
    @GetMapping("")
    public String selectLogs(OperationIAO operationIAO) {
      List<OperationIAO> list=osi.selectLogs(operationIAO);


       // List<Customer> list = csi.selectAllCustomers(customer);
        return JSON.toJSONString(list);
    }





}
