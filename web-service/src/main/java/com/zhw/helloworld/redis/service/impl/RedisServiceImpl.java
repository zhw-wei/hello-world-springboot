package com.zhw.helloworld.redis.service.impl;

import com.alibaba.fastjson.JSON;
import com.zhw.helloworld.common.config.CommonUtils;
import com.zhw.helloworld.redis.key.RedisKeyTemplate;
import com.zhw.helloworld.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * @author: zhaohw
 * @date: 2021.04.29 下午 3:47
 * @description:
 */
@Slf4j
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public boolean add(RedisKeyTemplate redis) {
        this.stringRedisTemplate.opsForValue()
                .set(redis.getKey(), redis.getValue(), redis.getTimeOut(), TimeUnit.SECONDS);
        return true;
    }

    @Override
    public boolean delete(RedisKeyTemplate redis) {
        return this.stringRedisTemplate.delete(redis.getKey());
    }

    @Override
    public boolean update(RedisKeyTemplate redis) {
        return this.add(redis);
    }

    @Override
    public <T> T get(RedisKeyTemplate redis, Class<T> tClass) {
        String str = this.stringRedisTemplate.opsForValue().get(redis.getKey());
        return JSON.parseObject(str, tClass);
    }

    @Override
    public <T> T getOrDefault(RedisKeyTemplate redis, Class<T> tClass, T defValue) {
        String str = this.stringRedisTemplate.opsForValue().get(redis.getKey());
        if (Objects.isNull(str)) return defValue;
        return JSON.parseObject(str, tClass);
    }
}
