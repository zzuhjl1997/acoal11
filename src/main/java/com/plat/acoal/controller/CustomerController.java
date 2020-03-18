package com.plat.acoal.controller;
import com.alibaba.fastjson.JSON;
import com.plat.acoal.entity.Customer;
import com.plat.acoal.service.CustomerService;
import com.plat.acoal.service.impl.CustomerServiceImpl;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;
@RestController
@Log4j2
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    public CustomerServiceImpl csi;
    @GetMapping("")
    public String selectAllCustomer(Customer customer) {
        // User user=new User();
        // Customer customer=new Customer();
        //log.settaction(request.getmethod);
        //request.getRequestURI();
        List<Customer> list = csi.selectAllCustomers(customer);
        //在这@auwired   operationlog
        return JSON.toJSONString(list);
    }
}
