package com.zhw.helloworld.demo.impl;

import com.zhw.helloworld.demo.HelloworldService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaohw
 * @date: 2021.04.23 上午 10:59
 * @description:
 */
@Slf4j
@Service
public class HelloworldServiceImpl implements HelloworldService {


    @Override
    public String hello() {
        return "hello world";
    }
}
