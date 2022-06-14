package com.zhw.helloworld.service;

import com.zhw.helloworld.dubbo.api.UserInfoService;
import com.zhw.helloworld.dubbo.dto.UserInfoRequest;
import com.zhw.helloworld.dubbo.dto.UserInfoResponse;
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

    @Override
    public UserInfoResponse getUserInfo(UserInfoRequest request) {
        UserInfoResponse response = new UserInfoResponse();
        response.setUserId(request.getUserId());
        response.setUserName(response.getUserName());
        response.setUserCode("hello-world-02");
        response.setPhone("98765432101");
        return response;
    }
}
