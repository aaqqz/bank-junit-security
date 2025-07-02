package com.toy.bank.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@AutoConfigureMockMvc
//@WebMvcTest
@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void authentication_test() throws Exception {
        // given

        // when // then todo
        mockMvc.perform(MockMvcRequestBuilders.get("/api/s/test"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void authorization_test() {
        // given

        // when

        // then
    }
}