package com.zhw.helloworld.controller;

import com.zhw.helloworld.dubbo.api.UserInfoService;
import com.zhw.helloworld.dubbo.dto.UserInfoRequest;
import com.zhw.helloworld.dubbo.dto.UserInfoResponse;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhw
 * @date 2022/6/13 23:24
 */
@RequestMapping("/user-info")
@RestController
public class UserInfoController {

    @DubboReference(version = UserInfoService.version0)
    private UserInfoService userInfoService;

    @DubboReference(version = UserInfoService.version1)
    private UserInfoService userInfoService2;

    @GetMapping("/getUserInfo")
    public String getUserInfo(){
        return this.userInfoService.getUserInfo();
    }

    @GetMapping("/getUserInfo2")
    public String getUserInfo2(){
        return this.userInfoService2.getUserInfo();
    }

    @GetMapping("/getUserInfo3")
    public UserInfoResponse getUserInfo3(){
        UserInfoRequest request = new UserInfoRequest();
        request.setUserId(-1);
        request.setUserName("hello");
        return this.userInfoService.getUserInfo(request);
    }

    @GetMapping("/getUserInfo4")
    public UserInfoResponse getUserInfo4(){
        UserInfoRequest request = new UserInfoRequest();
        request.setUserId(-2);
        request.setUserName("world");
        return this.userInfoService2.getUserInfo(request);
    }
}
