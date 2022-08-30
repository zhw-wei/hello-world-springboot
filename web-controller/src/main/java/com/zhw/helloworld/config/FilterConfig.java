package com.zhw.helloworld.config;

import com.zhw.helloworld.xssFilter.RefererFilter;
import com.zhw.helloworld.xssFilter.XssFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

/**
 * @author: zhaohw
 * @date: 2022.08.30 上午 11:03
 */
@Configuration
public class FilterConfig {

    /**
     * 配置过滤器 -- refererFilter
     */
    @Bean
    @Order(Integer.MIN_VALUE + 1)
    public FilterRegistrationBean refererFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new RefererFilter());
        registration.addUrlPatterns("/*");
        registration.setName("refererFilter");
        return registration;
    }

    /**
     * 配置过滤器 -- xssFilter
     */
    @Bean
    @Order(Integer.MIN_VALUE + 2)
    public FilterRegistrationBean xssFilterRegistration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
        registration.setFilter(new XssFilter());
        registration.addUrlPatterns("/*");
        registration.addInitParameter("enabled", "true");
        registration.setName("xssFilter");
        return registration;
    }
}
