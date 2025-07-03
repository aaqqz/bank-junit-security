package com.toy.bank.service;

import com.toy.bank.domain.user.UserEnum;
import com.toy.bank.service.dto.UserCreateServiceRequest;
import com.toy.bank.service.dto.UserResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
class UserServiceTest {

    private static final Logger log = LoggerFactory.getLogger(UserServiceTest.class);
    @Autowired
    UserService userService;

    @Test
    void 회원가입() {
        // given
        UserCreateServiceRequest request = UserCreateServiceRequest.builder()
                .name("testUser")
                .password("1234")
                .email("test@toy.com")
                .fullName("테스트 유저")
                .build();

        // when
        UserResponse userResponse = userService.create(request);

        // then
        Assertions.assertThat(userResponse).isNotNull();
        Assertions.assertThat(userResponse.getName()).isEqualTo("testUser");
        Assertions.assertThat(userResponse.getRole()).isEqualTo(UserEnum.CUSTOMER);

        Assertions.assertThat(userResponse)
                .extracting("id", "name", "email", "fullName", "role")
                .contains(1L, "testUser", "test@toy.com", "테스트 유저", UserEnum.CUSTOMER);
    }
}