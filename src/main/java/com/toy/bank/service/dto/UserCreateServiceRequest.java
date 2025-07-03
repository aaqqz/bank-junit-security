package com.toy.bank.service.dto;

import com.toy.bank.domain.user.User;
import com.toy.bank.domain.user.UserEnum;
import lombok.Builder;
import lombok.Getter;

@Getter
public class UserCreateServiceRequest {

    private final String name;
    private final String password;
    private final String email;
    private final String fullName;

    @Builder
    public UserCreateServiceRequest(String name, String password, String email, String fullName) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
    }

    public User toEntity() {
        return User.builder()
                .name(name)
                .password(password)
                .email(email)
                .fullName(fullName)
                .role(UserEnum.CUSTOMER)
                .build();
    }
}
