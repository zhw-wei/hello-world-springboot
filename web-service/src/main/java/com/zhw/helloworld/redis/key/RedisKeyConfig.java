package com.zhw.helloworld.redis.key;

import java.util.function.BiFunction;
import java.util.function.Function;

/**
 * @author: zhaohw
 * @date: 2021.04.29 下午 5:16
 * @description:
 */
public interface RedisKeyConfig {

    Function<String, RedisKeyTemplate> GET = key -> RedisKeyTemplate.forGetOrDelete(key);

    Function<String, RedisKeyTemplate> DELETE = key -> RedisKeyTemplate.forGetOrDelete(key);

    BiFunction<String, Object, RedisKeyTemplate> ADD = (key, value) ->
            RedisKeyTemplate.forAddOrUpdate(key, value, 100);

    Function<Object, RedisKeyTemplate> ADD_HELLO = value ->
            RedisKeyTemplate.forAddOrUpdate("HELLO_KEY", value, 100);
}
