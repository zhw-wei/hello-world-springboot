package com.zhw.helloworld.config;

import com.zhw.helloworld.hello.service.HelloService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.core.Ordered;

/**
 * 自定义后置处理器
 * @author: zhaohw
 * @date: 2022.05.13 下午 4:15
 */
@Slf4j
public class HelloBeanPostProcessor implements BeanPostProcessor, Ordered {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof HelloService) {
            log.debug("HelloBeanPostProcessor.postProcessBeforeInitialization, bean: {}, beanName{}",
                    bean, beanName);
        }

        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {

        if(bean instanceof HelloService) {
            log.debug("HelloBeanPostProcessor.postProcessAfterInitialization, bean: {}, beanName{}",
                    bean, beanName);
        }

        return bean;
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
