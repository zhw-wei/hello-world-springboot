package com.zhw.helloworld.hello.service.impl;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: zhaohw
 * @date: 2022.05.13 下午 4:41
 */
@Slf4j
public class HelloDataService {

    private String name;
    private String age;

    public HelloDataService(String name, String age){
        this.name = name;
        this.age = age;
    }

    public void printData(){
        log.info("name: {}, age: {}", name, age);

    }
}
