package com.toy.bank.controller.dto;

import com.toy.bank.service.dto.UserCreateServiceRequest;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class UserCreateRequest {

    // 영문 및 숫자만 허용, 길이 2~20자 이내
    @Size(min = 2, max = 20, message = "이름은 길이가 2~20자 이내여야 합니다.")
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "이름은 영문 및 숫자만 허용됩니다.")
    @NotBlank(message = "이름은 필수입니다.")
    private final String name;

    @Size(min = 2, max = 20, message = "비밀번호 길이가 2~20자 이내여야 합니다.")
    @NotBlank(message = "비밀번호는 필수입니다.")
    private final String password;

    @Email(message = "유효한 이메일 형식이 아닙니다.")
    @Size(min = 2, max = 20, message = "이메일은 길이가 2~20자 이내여야 합니다.")
    @NotBlank(message = "이메일은 필수입니다.")
    private final String email;

    @Size(min = 2, max = 20, message = "전체 이름은 길이가 2~20자 이내여야 합니다.")
    @NotBlank(message = "전체 이름은 필수입니다.")
    private final String fullName;

    public UserCreateServiceRequest toServiceRequest() {
        return UserCreateServiceRequest.builder()
                .name(name)
                .password(password)
                .email(email)
                .fullName(fullName)
                .build();
    }
}
