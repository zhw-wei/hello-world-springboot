package com.zhw.helloworld.service;

import com.zhw.helloworld.dubbo.api.UserInfoService;
import org.apache.dubbo.config.annotation.DubboService;

/**
 * @author zhw
 * @date 2022/6/13 23:24
 */
@DubboService(version = UserInfoService.version0)
public class UserInfoServiceImpl implements UserInfoService {
    @Override
    public String getUserInfo() {
        return "hello world user info v1";
    }
}
