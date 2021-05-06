package com.zhw.helloworld.mongo.hello.repository;

import com.zhw.helloworld.mongo.hello.model.HelloMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: zhaohw
 * @date: 2021.05.06 上午 11:46
 * @description:
 */
@Repository
public interface HelloRepository extends MongoRepository<HelloMongo, String> {

    HelloMongo save(HelloMongo helloMongo);

    HelloMongo selectById(String id);
}
