package com.plat.acoal.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {
    /*
    *跨域访问配置，前后端分离必配
     */
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        //允许某些域名使用资源
        corsConfiguration.addAllowedOrigin("*");
        //允许所有头信息
        corsConfiguration.addAllowedHeader("*");
        //允许所有方法访问（post、get等）
        corsConfiguration.addAllowedMethod("*");
        //开启后与ajax相呼应，能保证cookie的传递，保持session的一致性。
        //如果设为false，则每个ajax请求就是一个session，cookie始终为空
        corsConfiguration.setAllowCredentials(true);
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig());
        return new CorsFilter(source);
    }
}