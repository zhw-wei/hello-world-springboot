package com.zhw.helloworld.config;

import com.zhw.helloworld.common.dto.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 5:29
 * @description: 异常拦截
 */
@Slf4j
@RestControllerAdvice
public class ControllerAdviceConfig {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result bindException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder errorMesssage = new StringBuilder("参数校验失败: ");
        errorMesssage.append(
                bindingResult.getFieldErrors().stream()
                        .map(error -> String.format("%s : %s", error.getField(), error.getDefaultMessage()))
                        .collect(Collectors.joining(",")));

        log.error("{}", errorMesssage);

        return Result.Fail.FAIL(errorMesssage.toString());
    }

    @ExceptionHandler(Exception.class)
    public <T> Result<T> exception(Exception ex) {
        log.error("{}", ex);
        return Result.Fail.FAIL(ex.getMessage());
    }
}
