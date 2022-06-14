package com.zhw.helloworld.dubbo.api;

import com.zhw.helloworld.dubbo.dto.UserInfoRequest;
import com.zhw.helloworld.dubbo.dto.UserInfoResponse;

/**
 * @author: zhaohw
 * @date: 2022.06.13 下午 1:53
 */
public interface UserInfoService {

    String version0 = "v1.0";
    String version1 = "v2.0";

    String getUserInfo();

    UserInfoResponse getUserInfo(UserInfoRequest request);
}
