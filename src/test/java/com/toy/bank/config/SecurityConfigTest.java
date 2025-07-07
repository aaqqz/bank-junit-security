package com.toy.bank.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest
class SecurityConfigTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void authentication_test() throws Exception {
        // given

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/s/test"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("FORBIDDEN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("FORBIDDEN"));
    }

    @Test
    void authorization_test() throws Exception {
        // given
        // todo 로그인 개발 후 권한 test code 추가 필요 (권한이 있는경우, 권한이 없는 경우)
        // when // then
        mockMvc.perform(MockMvcRequestBuilders.get("/api/admin/test"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().is(401))
                .andExpect(MockMvcResultMatchers.jsonPath("$.errorCode").value("FORBIDDEN"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("FORBIDDEN"));
    }
}