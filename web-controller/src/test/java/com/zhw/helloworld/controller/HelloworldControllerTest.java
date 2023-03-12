package com.zhw.helloworld.controller;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

/**
 * @author: zhaohw
 * @date: 2021.04.25 上午 11:08
 * @description:
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
@DisplayName("hello world 接口测试")
public class HelloworldControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUpMockMvc() {

    }

    @Test
    @DisplayName("hello 接口测试")
    public void hello() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/hello-world/hello")
                        .param("id", "10")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("0"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    @DisplayName("world 接口测试")
    public void world() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/hello-world/world")
                        .param("id", "1")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("code").value("0"))
                .andDo(MockMvcResultHandlers.print());
    }
}
