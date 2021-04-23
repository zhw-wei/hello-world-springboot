package com.zhw.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author: zhaohw
 * @date: 2021.04.23 下午 2:43
 * @description:
 */

@Slf4j
@SpringBootTest
public class JaspytTest {

    @Test
    public void createMysqlConfig(){
        BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
        textEncryptor.setPassword("hello-world");
        String userName = textEncryptor.encrypt("userName");
        String password = textEncryptor.encrypt("password");

        log.info("userName: {}, password: {}", userName, password);
    }
}
