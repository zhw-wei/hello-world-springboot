package com.zhw.helloworld.hello.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhw.helloworld.config.PageResult;
import com.zhw.helloworld.config.Result;
import com.zhw.helloworld.dal.hello.dao.HelloMapper;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.hello.service.HelloService;
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

    /**
     * 测试aop事务是否正常
     * @return
     */
    @Override
    public Result<Void> saveTransaction() {
        Hello hello = new Hello();
        hello.setId(1);
        hello.setName("hello01");
        hello.setCode("hello01");

        this.helloMapper.insert(hello);

        int i=0;
        int j = 10 / i;

        return Result.Success.ADD();
    }

    /**
     * 测试pageHelper分页查询
     */
    @Override
    public PageResult<Hello> queryPage() {
        Page<Hello> pageInfo = PageHelper.startPage(1, 10)
                .doSelectPage(() -> this.helloMapper.selectByPrimaryKey(1));

        return PageResult.createResult(pageInfo, pageInfo.getResult());
    }
}
