package com.zhw.helloworld.controller;

import com.zhw.helloworld.demo.HelloworldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: zhaohw
 * @date: 2021.04.23 上午 10:18
 * @description:
 */
@RestController
@RequestMapping("/hello-world")
public class HelloworldController {
    @Autowired
    private HelloworldService helloworldService;

    @GetMapping("/hello")
    public String hello(){
        return helloworldService.hello();
    }
}
