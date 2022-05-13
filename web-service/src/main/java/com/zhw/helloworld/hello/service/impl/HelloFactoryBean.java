package com.zhw.helloworld.hello.service.impl;

import lombok.Setter;
import org.springframework.beans.factory.FactoryBean;

/**
 * @author: zhaohw
 * @date: 2022.05.13 下午 4:42
 */
public class HelloFactoryBean implements FactoryBean<HelloDataService> {

    @Setter
    private String name;
    @Setter
    private String age;

    @Override
    public HelloDataService getObject() throws Exception {

        return new HelloDataService(this.name, this.age);
    }

    @Override
    public Class<?> getObjectType() {
        return HelloDataService.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
