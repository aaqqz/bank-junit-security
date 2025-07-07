package com.toy.bank.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.bank.controller.dto.UserCreateRequest;
import com.toy.bank.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@ActiveProfiles("test")
@WebMvcTest(controllers = UserController.class, excludeAutoConfiguration = SecurityAutoConfiguration.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private UserService userService;

    @Test
    void join_success_test() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .name("name")
                .password("1234")
                .email("email@email.com")
                .fullName("fullName")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/join")
                        .content(objectMapper.writeValueAsString(userCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("OK"));
    }

    @Test
    void join_fail_test() throws Exception {
        // given
        UserCreateRequest userCreateRequest = UserCreateRequest.builder()
                .name("name한글")
                .password("1234")
                .email("email@email.com")
                .fullName("fullName")
                .build();

        // when // then
        mockMvc.perform(MockMvcRequestBuilders.post("/users/join")
                        .content(objectMapper.writeValueAsString(userCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.statusCode").value("BAD_REQUEST"));
    }
}