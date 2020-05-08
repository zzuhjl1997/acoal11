package com.plat.acoal.entity;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@PropertySource("classpath:application.properties")

//将CD注入容器 初始化时容器就会找到这个实体
@Component
@ConfigurationProperties(prefix = "cd")
public class CD{
//    @Value("${name}")

    private String name;

    public String getName() {
        return name;
    }
}
