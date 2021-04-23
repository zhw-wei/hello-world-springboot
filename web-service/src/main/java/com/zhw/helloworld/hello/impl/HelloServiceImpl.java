package com.zhw.helloworld.hello.impl;

import com.zhw.helloworld.config.Result;
import com.zhw.helloworld.dal.hello.dao.HelloMapper;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.hello.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 4:39
 * @description:
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService {
    @Autowired
    private HelloMapper helloMapper;

    @Override
    public Result<Hello> hello(int id) {
        Optional<Hello> hello = Optional.ofNullable(this.helloMapper.selectByPrimaryKey(id));

        return Result.Success.QUERY(
                hello.orElseGet(() -> {
                    Hello h = new Hello();
                    h.setId(-1);
                    h.setCode("hello");
                    h.setName("hello");
                    return h;
                }));
    }
}
