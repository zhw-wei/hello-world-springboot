package com.zhw.helloworld.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class HttpUtils {

    private static RestTemplate REST_TEMPLATE = new RestTemplate();

    public static <T> T doGetForObject(String url, Map<String, Object> urlMap, Class<T> tClass) {
        String string = doGet(url, urlMap);
        return JSON.parseObject(string, tClass);
    }

    public static <T> List<T> doGetForArray(String url, Map<String, Object> urlMap, Class<T> tClass) {
        String string = doGet(url, urlMap);
        return JSON.parseArray(string, tClass);
    }

    public static <T> T doPostForObject(String url, Object paramsObject,
                                     Map<String, Object> urlMap, Class<T> tClass) {
        String string = doPost(url, paramsObject, urlMap);
        return JSON.parseObject(string, tClass);
    }

    public static <T> List<T> doPostForArray(String url, Object paramsObject,
                                          Map<String, Object> urlMap, Class<T> tClass) {
        String string = doPost(url, paramsObject, urlMap);
        return JSON.parseArray(string, tClass);
    }

    private static String doGet(String url, Map<String, Object> urlMap) {

        try {
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<>(headers);
            ResponseEntity<String> resp = REST_TEMPLATE.exchange(createUrl(url, urlMap), HttpMethod.GET, entity, String.class);

            JSONObject json = JSON.parseObject(resp.getBody());
            if (Objects.nonNull(json) && "0".equals(json.getString("code"))) {
                return JSON.toJSONString(json.get("data"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static String doPost(String url, Object paramsObject, Map<String, Object> urlMap) {
        try {

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.parseMediaType("application/json; charset=UTF-8"));
            headers.add("Accept", MediaType.APPLICATION_JSON.toString());

            HttpEntity<String> entity = new HttpEntity<>(JSON.toJSONString(paramsObject), headers);
            ResponseEntity<String> resp = REST_TEMPLATE.exchange(createUrl(url, urlMap), HttpMethod.POST, entity, String.class);
            JSONObject json = JSON.parseObject(resp.getBody());

            log.info("POST请求响应结果：{}", json);

            if (Objects.nonNull(json) && "0".equals(json.getString("code"))) {
                return JSON.toJSONString(json.get("data"));
            }
        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return null;
    }

    private static String createUrl(String url, Map<String, Object> urlMap) {

        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("CONTRACT_CENTER_REQUEST_TIME",
                ZonedDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));

        if (Objects.nonNull(urlMap)) paramMap.putAll(urlMap);
        String urlParams = paramMap.entrySet().stream()
                .map(entry -> String.format("%s=%s", entry.getKey(), entry.getValue()))
                .collect(Collectors.joining("&"));

        // www.aaa.com/bbb?ccc=ddd&eee=fff
        String urlResult = String.format("%s?%s", url, urlParams);
        log.info("请求URL地址: {}", urlResult);
        return urlResult;
    }

}