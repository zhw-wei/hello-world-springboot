package com.zhw.helloworld.service;

import com.zhw.helloworld.common.config.CommonUtils;
import com.zhw.helloworld.dal.hello.dao.HelloMapper;
import com.zhw.helloworld.dal.hello.model.Hello;
import com.zhw.helloworld.dal.hello.model.HelloCriteria;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhw
 * @date 2022/5/26 20:24
 */
@Slf4j
@SpringBootTest
@DisplayName("回滚功能测试")
//@TestPropertySource(properties = {"spring.config.location=classpath:/xxxx.yaml"})
public class HelloworldRollbackTest {

    @Autowired
    private HelloMapper helloMapper;

    @Test
    @Rollback
    @Transactional
    @DisplayName("执行测试完成之后数据回滚")
    public void testInsert(){

        //查询数据库表中数据的数量
        final HelloCriteria criteria = new HelloCriteria();
        criteria.createCriteria().andIdGreaterThan(0);
        final long count0 = this.helloMapper.countByExample(criteria);
        final List<Hello> helloList0 = this.helloMapper.selectByExample(criteria);
        log.info("count0: {}, helloList0: {}", count0, CommonUtils.OBJECT2STRING.apply(helloList0));

        Hello hello = new Hello();
        hello.setCode("hello-code");
        hello.setName("hello-name");

        //插入一条数据
        this.helloMapper.insertSelective(hello);

        //查询数据库表中数据的数量
        final long count1 = this.helloMapper.countByExample(criteria);
        final List<Hello> helloList1 = this.helloMapper.selectByExample(criteria);
        log.info("count0: {}, helloList1: {}", count1, CommonUtils.OBJECT2STRING.apply(helloList1));

        //第二次查询比第一次查询多一
        Assertions.assertThat(count0 == (count1 - 1));
    }
}
