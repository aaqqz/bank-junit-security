package com.toy.bank.config.jwt;

/**
 * JWT 관련 상수 클래스
 * SECRET > 노출되면 안된다
 * REFRESH_TOKEN (적용 X)
 */
public class JwtConstants {

    public static final String SECRET = "secret"; // HS256 대칭키
    public static final int EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; // 7일
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER  = "Authorization";
}
