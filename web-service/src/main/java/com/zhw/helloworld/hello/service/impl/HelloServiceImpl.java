package com.zhw.helloworld.hello.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.zhw.helloworld.common.dto.PageResult;
import com.zhw.helloworld.common.dto.Result;
import com.zhw.helloworld.dal.hello.dao.HelloMapper;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.hello.service.HelloService;
import com.zhw.helloworld.mongo.hello.model.HelloMongo;
import com.zhw.helloworld.mongo.hello.repository.HelloRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.context.ServletContextAware;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import java.util.Optional;
import java.util.UUID;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 4:39
 * @description:
 */
@Slf4j
@Service
public class HelloServiceImpl implements HelloService, ServletContextAware {
    @Autowired
    private HelloMapper helloMapper;
    @Autowired
    private HelloRepository helloRepository;
    @Autowired
    private HelloDataService helloDataService;

    private ServletContext servletContext;

    @PostConstruct
    public void init(){
        this.helloDataService.printData();
    }

    @Override
    public Result<Hello> hello(int id) {
        Optional<Hello> hello = Optional.ofNullable(this.helloMapper.selectByPrimaryKey(id));
        log.info("scala {}", com.zhw.helloworld.common.Hello.hello("hello world"));

        return Result.Success.query(
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

        return Result.Success.add();
    }

    /**
     * 测试pageHelper分页查询
     */
    @Override
    public PageResult<Hello> queryPage() {
        Page<Hello> page = PageHelper.startPage(1, 10)
                .doSelectPage(() -> this.helloMapper.selectByPrimaryKey(1));

        return PageResult.createResult(page.getPageNum(), page.getPageSize(), page.getTotal(), page.getResult());
    }

    @Override
    public Result<HelloMongo> testMongo() {
        HelloMongo helloMongo = new HelloMongo();
        helloMongo.setId(UUID.randomUUID().toString());
        helloMongo.setName("hello mongo");
        helloMongo.setCode("hello mongo");
        this.helloRepository.save(helloMongo);

        return Result.Success.query(this.helloRepository.findByName(helloMongo.getName()).get(0));
    }

    //配置感知器，获取spring容器
    @Override
    public void setServletContext(ServletContext servletContext) {

        log.info("servletContext: {}", servletContext);
        this.servletContext = servletContext;
    }
}
