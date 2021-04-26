package com.zhw.helloworld;

import com.zhw.helloworld.config.Result;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.dal.world.model.World;
import com.zhw.helloworld.hello.service.HelloService;
import com.zhw.helloworld.world.service.WorldService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: zhaohw
 * @date: 2021.04.26 上午 10:48
 * @description:
 */
@Slf4j
@SpringBootTest
public class HelloworldServiceTest {

    @Autowired
    private HelloService helloService;
    @Autowired
    private WorldService worldService;

    @Test
    public void hello(){
        int id = 1;
        Result<Hello> result = this.helloService.hello(id);
        Assertions.assertEquals(result.getCode(), 0, "结果状态错误");
        Assertions.assertEquals(result.getData().getId(), id, "结果id错误");
    }

    @Test
    public void world(){
        int id = 1;
        Result<World> result = this.worldService.world(id);
        Assertions.assertEquals(result.getCode(), 0, "结果状态错误");
        Assertions.assertEquals(result.getData().getId(), id, "结果id错误");
    }
}
