package com.zhw.helloworld.config;

import lombok.extern.slf4j.Slf4j;

import java.time.ZonedDateTime;
import java.util.AbstractMap;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;

/**
 * 请求统计，每10分钟上报一次统计结果
 * 正常情况下，每分钟都会有请求进来，所以不用考虑超过10分钟无请求的情况
 *
 * @author: zhaohw
 * @date: 2022.03.08 上午 11:56
 */
@Slf4j
public class RequestStatistics {

    private static final int SIZE = 2;
    private static int INDEX;
    private static Supplier<Integer> INDEX_SUPPLIER = () -> (ZonedDateTime.now().getMinute() / 10) % SIZE;

    private static Map<String, Integer>[] REQUEST_TIMES = new Map[SIZE];

    static {
        for (int i = 0; i < SIZE; i++) REQUEST_TIMES[i] = new HashMap<>();
        INDEX = INDEX_SUPPLIER.get();
    }

    public static void statistics(String className, String methodName) {

        int index = INDEX_SUPPLIER.get();
        String key = String.format("%s.%s", className, methodName);

        //累加请求次数
        Map<String, Integer> map = REQUEST_TIMES[index];
        Integer times = map.getOrDefault(key, 0) + 1;
        map.put(key, times);

        printAndCleanLastMap(index);
    }

    private static void printAndCleanLastMap(int index){
        //上报请求次数
        if (index != INDEX) {
            INDEX = index;
            int lastIndex = index == 0 ? REQUEST_TIMES.length - 1 : index - 1;
            Map<String, Integer> lastMap = REQUEST_TIMES[lastIndex];
            if(lastMap.isEmpty()) return;

            int size = lastMap.size();
            int total = lastMap.values().stream().reduce(0, (v0, v1) -> v0 + v1);
            Map.Entry<String, Integer> maxEntry = lastMap.entrySet()
                    .stream().max(Comparator.comparingInt(Map.Entry::getValue))
                    .orElse(new AbstractMap.SimpleEntry<>("未知接口", 0));
            Map.Entry<String, Integer> minEntry = lastMap.entrySet()
                    .stream().min(Comparator.comparingInt(Map.Entry::getValue))
                    .orElse(new AbstractMap.SimpleEntry<>("未知接口", 0));
            String topTen = lastMap.entrySet().stream()
                    .sorted((entry0, entry1) -> entry1.getValue() - entry0.getValue()).limit(10)
                    .map(entry -> String.format("[%s-%s]", entry.getKey(), entry.getValue()))
                    .collect(Collectors.joining(","));


            log.info("接口调用结果统计：共调用 {} 个接口，所有接口被调用次数 {}，每个接口平均调用次数 {}" +
                    "最大调用次数是 {} 接口 {} 次，最小调用次数是 {} 接口 {} 次，前10大调用频率最高的接口是：{}",
                    size, total, (total*1.0)/size,
                    maxEntry.getKey(), maxEntry.getValue(), minEntry.getKey(), minEntry.getValue(), topTen);

            //清空上一组结果
            lastMap.clear();
        }
    }

}
