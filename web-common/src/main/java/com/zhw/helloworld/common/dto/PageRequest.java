package com.zhw.helloworld.common.dto;

import lombok.Data;

/**
 * @author: zhaohw
 * @date: 2021.04.26 下午 3:03
 * @description: 分页查询公共父类
 */
@Data
public class PageRequest {

    private int pageNo = 1;

    private int pageSize = 10;
}
