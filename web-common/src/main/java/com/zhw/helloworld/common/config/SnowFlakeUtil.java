package com.zhw.helloworld.common.config;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.SystemUtils;
import xyz.downgoon.snowflake.Snowflake;

import java.net.Inet4Address;
import java.net.UnknownHostException;
import java.util.Objects;
import java.util.Random;

/**
 * @author: zhaohw
 * @date: 2021.04.21 下午 3:01
 * @description:
 */
@Slf4j
public final class SnowFlakeUtil {
    private static Snowflake snowflake;

    static {
        long workerId = getWorkId();
        long dataCenterId = getDataCenterId();
        log.info("workerId: {}, dataCenterId: {}", workerId, dataCenterId);
        snowflake = new Snowflake(dataCenterId, workerId);
    }

    public static long createId(){
        return snowflake.nextId();
    }

    private static Long getWorkId(){
        try {
            String hostAddress = Inet4Address.getLocalHost().getHostAddress();
            int[] ints = StringUtils.toCodePoints(hostAddress);
            int sums = 0;
            for(int b : ints){
                sums += b;
            }
            return (long)(sums % 32);
        } catch (UnknownHostException e) {
            // 如果获取失败，则使用随机数备用
            return RandomUtils.nextLong(0,31);
        }
    }

    private static Long getDataCenterId(){
        int[] ints = StringUtils.toCodePoints(SystemUtils.getHostName());
        int sums = 0;
        if(Objects.isNull(ints)) {
            sums = new Random().nextInt();
            return (long)sums;
        }
        for (int i: ints) {
            sums += i;
        }
        return (long)(sums % 32);
    }
}
