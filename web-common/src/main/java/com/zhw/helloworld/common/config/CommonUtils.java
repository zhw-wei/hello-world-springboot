package com.zhw.helloworld.common.config;

import com.alibaba.fastjson2.JSON;

import java.util.function.Function;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 6:29
 * @description:
 */
public interface CommonUtils {

    Function<Object, String>  OBJECT2STRING = JSON::toJSONString;
}
