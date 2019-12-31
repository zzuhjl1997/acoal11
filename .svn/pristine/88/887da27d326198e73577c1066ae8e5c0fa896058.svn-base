package com.plat.acoal;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

//此注解最为重要，被注解的类为SpringBoot的启动类，一般不用改动
@SpringBootApplication
//MapperScan是自动扫描dao层的注解，不需要再对每个dao进行注解
@MapperScan("com.plat.acoal.dao")
//针对实体类，用处同上
@EntityScan("com.plat.acoal.entity")
public class AcoalApplication {
    public static void main(String[] args) {
        SpringApplication.run(AcoalApplication.class, args);
    }
}
