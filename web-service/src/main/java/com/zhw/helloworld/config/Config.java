package com.zhw.helloworld.config;

import com.zhw.helloworld.hello.service.impl.HelloFactoryBean;
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

    //使用factoryBean注册组件
    @Bean
    public HelloFactoryBean helloFactoryBean(){
        HelloFactoryBean factoryBean = new HelloFactoryBean();
        factoryBean.setName("helloFactoryName");
        factoryBean.setAge("helloFactoryAge");
        return factoryBean;
    }
}
