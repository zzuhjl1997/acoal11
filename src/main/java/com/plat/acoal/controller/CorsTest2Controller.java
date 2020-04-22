package com.plat.acoal.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/demo2")
    @RestController
//@CrossOrigin //所有域名均可访问该类下所有接口
    @CrossOrigin("https://blog.csdn.net") // 只有指定域名可以访问该类下所有接口
public class CorsTest2Controller {
        @GetMapping("/sayHello")
        public String sayHello() {
            return "hello world --- 2";
        }
    }


