package com.zhw.helloworld;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author: zhaohw
 * @date: 2022.06.13 下午 1:57
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableDubbo
public class DubbboServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(DubbboServerApplication.class, args);
    }
}
