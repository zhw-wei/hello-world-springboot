package com.zhw.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockReset;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.web.servlet.MockMvc;
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
public class HelloworldControllerTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;
    private MockHttpSession session;

    @BeforeEach
    public void setUpMockMvc() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
        //登录
        this.session = new MockHttpSession();
        this.session.setAttribute("user", "user");
    }

    @Test
    public void hello() throws Exception {

        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/hello-world/hello")
                        .param("id", "10")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void world() throws Exception {
        this.mockMvc.perform(
                MockMvcRequestBuilders.get("/hello-world/world")
                        .param("id", "abc")
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());
    }
}
