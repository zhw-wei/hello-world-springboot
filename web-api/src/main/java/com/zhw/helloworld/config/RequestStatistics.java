package com.zhw.helloworld.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * 请求统计，每10分钟上报一次统计结果
 * 正常情况下，每分钟都会有请求进来，所以不用考虑超过10分钟无请求的情况
 *
 * @author: zhaohw
 * @date: 2022.03.08 上午 11:56
 */
@Slf4j
@Component
public class RequestStatistics {

    // 0 ~ 5
    private static Map<String, Integer>[] REQUEST_TIMES = new Map[]{
            new HashMap(), new HashMap(), new HashMap(),
            new HashMap(), new HashMap(), new HashMap(),
    };

    private static int INDEX = 0;

    public void statistics(String className, String methodName) {

        ZonedDateTime now = ZonedDateTime.now();
        String time = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));

        int index = now.getMinute() / 10;
        String key = String.format("%s.%s", className, methodName);

        //累加请求次数
        Map<String, Integer> map = REQUEST_TIMES[index];
        Integer times = map.getOrDefault(key, 0);
        map.put(key, times);

        //上报请求次数
        if (index != INDEX) {
            INDEX = index;
            int lastIndex = index == 0 ? REQUEST_TIMES.length - 1 : index - 1;
            Map<String, Integer> lastMap = REQUEST_TIMES[lastIndex];
            log.info("current time is : {}", time);
            log.info("last request times is : {}", lastMap);

            //另起一个线程，保存请求次数结果

            //清空上一组结果
            lastMap.clear();
        }
    }
}
