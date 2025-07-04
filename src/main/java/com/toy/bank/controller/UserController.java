package com.toy.bank.controller;

import com.toy.bank.common.dto.ApiResponse;
import com.toy.bank.controller.dto.UserCreateRequest;
import com.toy.bank.service.UserService;
import com.toy.bank.service.dto.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/join")
    public ApiResponse<UserResponse> join(@RequestBody @Valid UserCreateRequest request) {
        UserResponse userResponse = userService.create(request.toServiceRequest());
        return ApiResponse.success(userResponse);
    }
}