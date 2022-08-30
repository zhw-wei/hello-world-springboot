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
    private final int code;
    @Getter
    private final String message;
    @Getter
    private final T data;

    private Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public static class Success {

        private static Result<Void> UPDATE = success("更新成功", null);
        private static Result<Void> DELETE = success("删除成功", null);
        private static Result<Void> ADD = success("添加成功", null);

        public static <T> Result<T> query(T data) {
            return success("查询成功", data);
        }

        public static Result<Void> update() {
            return UPDATE;
        }

        public static Result<Void> delete() {
            return DELETE;
        }

        public static Result<Void> add() {
            return ADD;
        }

        static <T> Result<T> success(String message, T data) {
            return new Result<>(CommonConfig.SUCCESS_CODE, message, data);
        }
    }

    public static class Fail {
        static <T> Result<T> fail(String message) {
            return fail(message, null);
        }

        static <T> Result<T> fail(String message, T data) {
            return new Result<>(CommonConfig.FAIL_CODE, message, data);
        }
    }
}
