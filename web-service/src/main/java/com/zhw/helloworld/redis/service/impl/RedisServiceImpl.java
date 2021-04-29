package com.zhw.helloworld.redis.service.impl;

import com.zhw.helloworld.redis.key.RedisKeyTemplate;
import com.zhw.helloworld.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author: zhaohw
 * @date: 2021.04.29 下午 3:47
 * @description:
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Override
    public boolean add(RedisKeyTemplate redis) {
        return false;
    }

    @Override
    public boolean delete(RedisKeyTemplate redisKeyTemplate) {
        return false;
    }

    @Override
    public boolean update(RedisKeyTemplate redisKeyTemplate) {
        return false;
    }

    @Override
    public <T> T get(RedisKeyTemplate redis, Class<T> tClass) {
        return null;
    }

    @Override
    public <T> T getOrDefault(RedisKeyTemplate redis, Class<T> tClass, T defValue) {
        return null;
    }
}
