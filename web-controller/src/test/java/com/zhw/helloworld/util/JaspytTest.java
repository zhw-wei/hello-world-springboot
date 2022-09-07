package com.zhw.helloworld.util;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 2:43
 * @description:
 */

@Slf4j
public class JaspytTest {

    @Test
    @Disabled
    public void createMysqlConfig(){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("hello-world");
        //不同环境密码保持一致
        String userName = textEncryptor.encrypt("helloworld");
        String password = textEncryptor.encrypt("Helloworld@dmin123");

        log.info("userName: {}, password: {}", userName, password);
    }
}
