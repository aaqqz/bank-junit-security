package com.toy.bank.config.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.toy.bank.config.auth.LoginUser;
import com.toy.bank.domain.user.User;
import com.toy.bank.domain.user.UserEnum;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

@Slf4j
public class JwtProcess {

    public static String createToken(LoginUser loginUser) {
        String jwtToken = JWT.create()
                .withSubject("" + loginUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + JwtConstants.EXPIRATION_TIME))
                .withClaim("id", loginUser.getUser().getId())
                .withClaim("role", loginUser.getUser().getRole().name())
                .sign(Algorithm.HMAC512(JwtConstants.SECRET));
        return jwtToken;
    }

    public static LoginUser verifyToken(String token) {
        DecodedJWT decodedJWT = JWT.require(Algorithm.HMAC512(JwtConstants.SECRET)).build().verify(token);
        Long id = decodedJWT.getClaim("id").asLong();
        String role = decodedJWT.getClaim("role").asString();
        // todo refactoring: User Repository를 사용하지 않고, 직접 User 객체를 생성
        User user = User.builder()
                .role(UserEnum.valueOf(role))
                .build();
        return new LoginUser(user);
    }
}
