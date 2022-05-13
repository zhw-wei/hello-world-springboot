package com.zhw.helloworld.processor;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author: zhaohw
 * @date: 2022.05.13 下午 4:17
 */
@Configuration
public class Config {

    //配置后置处理器
    @Bean
    public HelloBeanPostProcessor helloBeanPostProcessor(){
        return new HelloBeanPostProcessor();
    }

    //配置后置处理器
    @Bean
    public WorldBeanPostProcessor worldBeanPostProcessor(){
        return new WorldBeanPostProcessor();
    }
}
