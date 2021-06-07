package com.zhw.helloworld.service;

import com.zhw.helloworld.common.config.CommonConfig;
import com.zhw.helloworld.common.config.CommonUtils;
import com.zhw.helloworld.common.dto.PageResult;
import com.zhw.helloworld.common.dto.Result;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.dal.world.model.World;
import com.zhw.helloworld.hello.service.HelloService;
import com.zhw.helloworld.world.service.WorldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: zhaohw
 * @date: 2021.04.26 上午 10:48
 * @description:
 */
@Slf4j
@SpringBootTest
@DisplayName("hello world 功能测试")
public class HelloworldServiceTest {

    @Autowired
    private HelloService helloService;
    @Autowired
    private WorldService worldService;

    @Test
    @DisplayName("hello 功能测试")
    public void hello(){
        int id = 1;
        Result<Hello> result = this.helloService.hello(id);
        Assertions.assertEquals(result.getCode(), 0, "结果状态错误");
        Assertions.assertEquals(result.getData().getId(), id, "结果id错误");
    }

    @Test
    @EnabledOnOs({OS.MAC})
    @DisplayName("world 功能测试")
    public void world(){
        int id = 1;
        Result<World> result = this.worldService.world(id);
        Assertions.assertEquals(result.getCode(), 0, "结果状态错误");
        Assertions.assertEquals(result.getData().getId(), id, "结果id错误");
    }

    @Test
    @DisplayName("world 分页功能测试")
    public void helloPage(){
        PageResult<Hello> pageResult = this.helloService.queryPage();
        Assertions.assertEquals(pageResult.getCode(), CommonConfig.SUCCESS_CODE);
    }
}
