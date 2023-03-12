package com.zhw.helloworld.controller;

import com.zhw.helloworld.common.dto.Result;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.dal.world.model.World;
import com.zhw.helloworld.hello.service.HelloService;
import com.zhw.helloworld.world.service.WorldService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(tags = "hello java 接口测试")
@RestController
@RequestMapping("/hello-world")
public class HelloworldController {

    @Autowired
    private HelloService helloService;
    @Autowired
    private WorldService worldService;

    @GetMapping("/hello")
    @ApiOperation("hello测试")
    public Result<Hello> hello(@RequestParam Integer id) {
        return this.helloService.hello(id);
    }

    @GetMapping("/world")
    @ApiOperation("world测试")
    public Result<World> world(@RequestParam Integer id) {
        return this.worldService.world(id);
    }

    @GetMapping("/transaction")
    @ApiOperation("transaction测试")
    public Result<Void> transaction() {
        return this.helloService.saveTransaction();
    }
}
