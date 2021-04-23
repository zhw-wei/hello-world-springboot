package com.zhw.helloworld.controller;

import com.zhw.helloworld.config.Result;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.dal.world.model.World;
import com.zhw.helloworld.hello.HelloService;
import com.zhw.helloworld.world.WorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    private HelloService helloService;
    @Autowired
    private WorldService worldService;

    @GetMapping("/hello")
    public Result<Hello> hello(@RequestParam Integer id){
        return this.helloService.hello(id);
    }

    @GetMapping("/world")
    public Result<World> world(@RequestParam Integer id){
        return this.worldService.world(id);
    }
}
