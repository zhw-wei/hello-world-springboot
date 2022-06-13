package com.zhw.helloworld.controller;

import com.zhw.helloworld.common.dto.Result;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotBlank;

/**
 * @author zhw
 * @date 2022/6/1 22:42
 */
@Slf4j
@RestController
@RequestMapping("/")
public class LoginController {

    @PostMapping("/doLogin")
    public Result<String> doLogin(@RequestBody @Validated LoginDto loginDto){
        /**
         * todo 增加代码，判断用户名和密码是否正确
         */

        //登陆成功
        final Subject subject = SecurityUtils.getSubject();
        try {
            //login()方法没有返回值，只能通过是否有异常判断是否登陆成功
            subject.login(new UsernamePasswordToken(loginDto.getUserName(), loginDto.getPassword()));

            //授权认证，判断是否有角色role1
            subject.checkRole("role1");

            if(log.isInfoEnabled()){
                log.info("[{}]登陆成功", loginDto.getUserName());
            }
            return Result.Success.SUCCESS("登陆成功", "登陆成功");
        }catch (Exception ex){
            if(log.isErrorEnabled()){
                log.error("[{}]登陆失败, ex: {}", loginDto.getUserName(), ex);
            }
        }
        return Result.Fail.FAIL("登陆失败");
    }

    @Data
    public static class LoginDto{
        @NotBlank
        private String userName;
        @NotBlank
        private String password;
    }
}
