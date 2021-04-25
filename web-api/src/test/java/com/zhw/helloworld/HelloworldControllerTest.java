package com.zhw.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

/**
 * @author: zhaohw
 * @date: 2021.04.25 上午 11:08
 * @description:
 */
@Slf4j
@SpringBootTest
@AutoConfigureMockMvc
public class HelloworldControllerTest {


    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUpMockMvc() {

    }

    @Test
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
