package com.zhw.helloworld.common.dto;

import com.zhw.helloworld.common.config.CommonConfig;
import lombok.Getter;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 5:14
 * @description: 返回对象统一结构
 */
public class Result<T> {
    @Getter
    private int code;
    @Getter
    private String message;
    @Getter
    private T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public interface Success {

        static <T> Result<T> QUERY(T data) {
            return SUCCESS("查询成功", data);
        }

        static <T> Result<T> UPDATE() {
            return SUCCESS("更新成功", null);
        }

        static <T> Result<T> DELETE() {
            return SUCCESS("删除成功", null);
        }

        static <T> Result<T> ADD() {
            return SUCCESS("添加成功", null);
        }

        static <T> Result<T> SUCCESS(String messsage, T data) {
            return new Result(CommonConfig.SUCCESS_CODE, messsage, data);
        }
    }

    public interface Fail {
        static <T> Result<T> FAIL(String message) {
            return FAIL(message, null);
        }

        static <T> Result<T> FAIL(String message, T data) {
            return new Result(CommonConfig.FAIL_CODE, message, data);
        }
    }
}
