package com.zhw.helloworld.config;

import com.github.pagehelper.Page;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

/**
 * @author: zhaohw
 * @date: 2021.04.26 下午 2:36
 * @description:
 */
@ToString
public class PageResult<T> {
    @Getter
    private int code;
    @Getter
    private String message;
    @Getter
    private PageObject<T> data;

    private PageResult(){}

    public static <T> PageResult<T> createResult(Page page, List<T> resultList){

        PageInfo pageInfo = new PageInfo();
        pageInfo.pageNo = page.getPageNum();
        pageInfo.pageSize = page.getPageSize();
        pageInfo.totalCount = page.getTotal();

        PageObject<T> pageObject = new PageObject<>();
        pageObject.pageInfo = pageInfo;
        pageObject.resultList = resultList;

        PageResult<T> result = new PageResult();
        result.code = CommonConfig.SUCCESS_CODE;
        result.message = "查询成功";
        result.data = pageObject;

        return result;
    }

    @ToString
    public static class PageObject<T> {
        @Getter
        private PageInfo pageInfo;
        @Getter
        private List<T> resultList;
    }

    @ToString
    public static class PageInfo {
        @Getter
        private int pageNo;
        @Getter
        private int pageSize;
        @Getter
        private long totalCount;
    }
}
