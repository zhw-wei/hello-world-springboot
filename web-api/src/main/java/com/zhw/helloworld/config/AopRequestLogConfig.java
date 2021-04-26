package com.zhw.helloworld.config;

import com.zhw.helloworld.common.config.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 5:59
 * @description:
 */
@Slf4j
@Aspect
@Component
public class AopRequestLogConfig {

    /**
     * 设置拦截点
     */
    @Pointcut("execution(public * com.zhw.helloworld.controller.*Controller.*(..))")
    public void controllerAspect() {
    }

    /**
     * 前置通知
     */
    @Before("controllerAspect()")
    public void doBefore(JoinPoint point) {
        log.info("开始处理请求=======================");
        String params = null;

        Object[] args = point.getArgs();
        if(Objects.nonNull(args)){
            params = Stream.of(args).map(arg -> CommonUtils.OBJECT2STRING.apply(arg))
                    .collect(Collectors.joining(", "));
        }

        log.info("请求开始，方法：{}.{}, 请求参数：{} ",
                point.getTarget().getClass().getName(), point.getSignature().getName(), params);
    }

    /**
     * 后置通知
     */
    @After("controllerAspect()")
    public void doAfter(JoinPoint point){

    }

    /**
     * 返回通知
     * @param point
     */
    @AfterReturning(value = "controllerAspect()", returning = "response")
    public void doAfterReturn(JoinPoint point, Object response){

        log.info("请求结束，方法：{}.{}, 返回参数：{} ",
                point.getTarget().getClass().getName(), point.getSignature().getName(),
                CommonUtils.OBJECT2STRING.apply(response));
    }

}
