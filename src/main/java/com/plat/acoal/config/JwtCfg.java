package com.plat.acoal.config;

import com.plat.acoal.filter.JwtFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author : LiuYang | Leon Willow
 * @date : 2020-04-26 08:53
 * @des ：JWT验证
 **/
@Configuration
public class JwtCfg {
    @Bean
    public FilterRegistrationBean<JwtFilter> jwtFilter(){
        final FilterRegistrationBean<JwtFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new JwtFilter());
        registrationBean.addUrlPatterns("/alarm/*");
        registrationBean.addUrlPatterns("/dev/*");
        registrationBean.addUrlPatterns("/fan/*");
        registrationBean.addUrlPatterns("/param/*");
        registrationBean.addUrlPatterns("/cannon/*");
        registrationBean.addUrlPatterns("/dust/*");
        registrationBean.addUrlPatterns("/gas/*");
        registrationBean.addUrlPatterns("/operationLog/*");
        registrationBean.addUrlPatterns("/pressureflow/*");
        registrationBean.addUrlPatterns("/temperature/*");
        registrationBean.addUrlPatterns("/user/*");
        registrationBean.addUrlPatterns("/region/*");
        registrationBean.addUrlPatterns("/mstonline/*");
        return registrationBean;
    }
}
