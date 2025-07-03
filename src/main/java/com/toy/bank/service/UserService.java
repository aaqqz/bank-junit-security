package com.toy.bank.service;

import com.toy.bank.common.exception.CustomException;
import com.toy.bank.common.exception.ErrorCode;
import com.toy.bank.domain.user.User;
import com.toy.bank.service.dto.UserCreateServiceRequest;
import com.toy.bank.domain.user.UserRepository;
import com.toy.bank.service.dto.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public UserResponse create(UserCreateServiceRequest request) {
        // 1, 동일 유저 이름 존재 검사
        userRepository.findByName(request.getName())
                .ifPresent(user -> {throw new CustomException(ErrorCode.ALREADY_EXISTS_USER);});

        // 2, 회원가입
        User savedUser = userRepository.save(request.toEntity());

        // 3, dto 응답
        return UserResponse.from(savedUser);
    }
}
