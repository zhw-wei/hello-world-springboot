package com.zhw.helloworld.dubbo.dto;

import lombok.Data;

import java.io.Serializable;

/**
 * @author: zhaohw
 * @date: 2022.06.14 下午 2:02
 */
@Data
public class UserInfoResponse implements Serializable {
    private Integer userId;
    private String userName;
    private String userCode;
    private String phone;
}
