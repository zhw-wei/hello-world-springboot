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
    public <T> Result<T> bindException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();

        StringBuilder msg = new StringBuilder("参数校验失败: ");
        msg.append(
                bindingResult.getFieldErrors().stream()
                        .map(error -> String.format("%s : %s", error.getField(), error.getDefaultMessage()))
                        .collect(Collectors.joining(",")));

        log.error("{}", msg);

<<<<<<< HEAD
        return Result.Fail.fail(errorMesssage.toString());
=======
        return Result.Fail.fail(msg.toString());
>>>>>>> 1a62d54ae5d3ec04e33531e82f539c78a3bc0f27
    }

    @ExceptionHandler(Exception.class)
    public <T> Result<T> exception(Exception ex) {
<<<<<<< HEAD
        log.error("{}", ex);
=======
        log.error("ex: {}", ex);
>>>>>>> 1a62d54ae5d3ec04e33531e82f539c78a3bc0f27
        return Result.Fail.fail(ex.getMessage());
    }
}
