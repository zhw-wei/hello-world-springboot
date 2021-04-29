package com.zhw.helloworld.redis.key;

import com.zhw.helloworld.common.config.CommonUtils;
import lombok.Getter;

/**
 * @author: zhaohw
 * @date: 2021.04.29 下午 3:47
 * @description:
 */
public final class RedisKeyTemplate {

    @Getter
    private String key;     //add update delete get

    @Getter
    private String value;   //add update

    @Getter
    private int timeOut;    //add update

    private RedisKeyTemplate(String key, Object value, int timeOut) {
        this.key = key;
        this.value = CommonUtils.OBJECT2STRING.apply(value);
        this.timeOut = timeOut;
    }

    protected static RedisKeyTemplate forGetOrDelete(String key){
        return new RedisKeyTemplate(key, null, -1);
    }

    protected static RedisKeyTemplate forAddOrUpdate(String key, Object object, int timeOut){
        return new RedisKeyTemplate(key, object, timeOut);
    }
}
