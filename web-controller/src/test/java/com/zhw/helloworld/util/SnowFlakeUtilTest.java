package com.zhw.helloworld.util;

import com.zhw.helloworld.common.config.SnowFlakeUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

/**
 * @author: zhaohw
 * @date: 2021.04.29 下午 3:44
 * @description:
 */
@Slf4j
public class SnowFlakeUtilTest {

    @Test
    @Disabled
    public void createId(){

        Long sid = SnowFlakeUtil.createId();
        log.info("sid: {}, sid length: {}", sid, String.valueOf(sid).length());
    }
}
