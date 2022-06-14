package com.zhw.helloworld.service;

import com.zhw.helloworld.dubbo.api.UserInfoService;
import com.zhw.helloworld.dubbo.dto.UserInfoRequest;
import com.zhw.helloworld.dubbo.dto.UserInfoResponse;
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

    @Override
    public UserInfoResponse getUserInfo(UserInfoRequest request) {
        UserInfoResponse response = new UserInfoResponse();
        response.setUserId(request.getUserId());
        response.setUserName(response.getUserName());
        response.setUserCode("hello-world-01");
        response.setPhone("12345678901");
        return response;
    }
}
