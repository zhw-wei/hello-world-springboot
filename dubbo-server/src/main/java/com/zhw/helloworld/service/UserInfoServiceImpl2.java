package com.zhw.helloworld.service;

import com.zhw.helloworld.dubbo.api.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zhw
 * @date 2022/6/13 23:31
 */
@DubboService(version = UserInfoService.version1)
public class UserInfoServiceImpl2 implements UserInfoService {
    @Override
    public String getUserInfo() {
        return "hello world user info v2";
    }
}
