package com.zhw.helloworld.hello.service;

import com.zhw.helloworld.common.dto.PageResult;
import com.zhw.helloworld.common.dto.Result;
import com.zhw.helloworld.dal.hello.model.Hello;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 4:38
 * @description:
 */
public interface HelloService {
    Result<Hello> hello(int id);

    Result<Void> saveTransaction();

    PageResult<Hello> queryPage();
}
