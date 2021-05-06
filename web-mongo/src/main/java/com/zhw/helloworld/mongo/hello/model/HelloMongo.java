package com.zhw.helloworld.mongo.hello.model;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author: zhaohw
 * @date: 2021.05.06 上午 11:48
 * @description:
 */
@Data
public class HelloMongo {
    @Id
    private String id;
    private String name;
    private String code;
}
