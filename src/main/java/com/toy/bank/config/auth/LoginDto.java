package com.toy.bank.config.auth;

import com.toy.bank.common.util.CustomDateUtil;
import com.toy.bank.domain.user.User;
import lombok.Getter;

import java.time.LocalDateTime;

public class LoginDto {

    @Getter
    public static class LoginRequest {
        private String name;
        private String password;
    }

    @Getter
    public static class LoginResponse {
        private Long id;
        private String name;
        private String createAt;

        private LoginResponse(Long id, String name, LocalDateTime createAt) {
            this.id = id;
            this.name = name;
//            this.createAt = createAt; // todo check
            this.createAt = CustomDateUtil.toStringFormat(createAt);
        }

        public static LoginResponse from(User user) {
            return new LoginResponse(user.getId(), user.getName(), user.getCreatedAt());
        }
    }
}
