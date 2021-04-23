package com.zhw.helloworld.config;

import com.alibaba.fastjson.JSON;

import java.util.function.Function;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 6:29
 * @description:
 */
public interface CommonUtils {

    Function<Object, String>  OBJECT2STRING = obj -> JSON.toJSONString(obj);
}
