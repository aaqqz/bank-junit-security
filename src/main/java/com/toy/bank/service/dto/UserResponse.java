package com.toy.bank.service.dto;

import com.toy.bank.domain.user.User;
import com.toy.bank.domain.user.UserEnum;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;

@Getter
@ToString
public class UserResponse {

    private final Long id;
    private final String name;
    private final String email;
    private final String fullName;
    private final UserEnum role;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    @Builder
    public UserResponse(Long id, String name, String email, String fullName, UserEnum role, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.fullName = fullName;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public static UserResponse from(User user) {
        return UserResponse.builder()
                .id(user.getId())
                .name(user.getName())
                .email(user.getEmail())
                .fullName(user.getFullName())
                .role(user.getRole())
                .createdAt(user.getCreatedAt())
                .updatedAt(user.getUpdatedAt())
                .build();
    }
}
