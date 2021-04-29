package com.zhw.helloworld.redis.service;

import com.zhw.helloworld.redis.key.RedisKeyTemplate;

/**
 * @author: zhaohw
 * @date: 2021.04.29 下午 3:46
 * @description:
 */
public interface RedisService {
    boolean add(RedisKeyTemplate redis);

    boolean delete(RedisKeyTemplate redisKeyTemplate);

    boolean update(RedisKeyTemplate redisKeyTemplate);

    <T> T get(RedisKeyTemplate redis, Class<T> tClass);

    <T> T getOrDefault(RedisKeyTemplate redis, Class<T> tClass, T defValue);
}
