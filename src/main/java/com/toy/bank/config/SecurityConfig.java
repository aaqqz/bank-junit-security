package com.toy.bank.config;

import com.toy.bank.domain.user.UserEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomAccessDeniedHandler customAccessDeniedHandler;

    // (@Configuration 클래스에 @Bean 어노테이션을 붙이면 해당 메서드가 반환하는 객체를 Ioc 컨테이너에 등록)
    // @Configuration 클래스에 있는 @Bean 만 작동한다.
    @Bean // Ioc 컨테이너에 BCryptPasswordEncoder 객체를 등록
    public BCryptPasswordEncoder passwordEncoder() {
        log.debug("[debug] BCryptPasswordEncoder 빈 등록");
        return new BCryptPasswordEncoder();
    }

    // JWT 서버를 만들 예정 > Session 사용 안함
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        log.debug("[debug] SecurityFilterChain() 빈 등록");

        http
                .csrf((csrfConfig) -> csrfConfig.disable()) // CSRF 보호 비활성화 (REST API 서버에서는 필요 없음)
                .headers((headerConfig) -> headerConfig.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable)) // iframe 허용 안함
                .cors((corsConfig) -> corsConfig.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // jSessionId 를 서버쪽에서 관리하지 않음
                .formLogin((formLogin) -> formLogin.disable())
                .httpBasic((httpBasic) -> httpBasic.disable()) // 브라우저가 팝업창을 이용해서 사용자 인증을 진행
                .authorizeHttpRequests((authorizeRequests) ->
                        authorizeRequests
                                .requestMatchers("/api/s/**").authenticated()
                                .requestMatchers("/api/admin/**").hasRole(UserEnum.ADMIN.name()) // 최근 공식 분서는 ROLE_ 접두사를 안붙여도
                                .anyRequest().permitAll()
                )
                .exceptionHandling(e -> e
                        .authenticationEntryPoint(customAuthenticationEntryPoint)
                        .accessDeniedHandler(customAccessDeniedHandler)
                )
        ;

        return http.build();
    }

    public CorsConfigurationSource corsConfigurationSource() {
        log.debug("[debug] CorsConfigurationSource cors 설정이 SecurityFilterChain 에 등록");

        CorsConfiguration configuration = new CorsConfiguration();
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*"); // 모든 HTTP 메서드 허용
        configuration.addAllowedOriginPattern("*"); // 모든 IP 허용
        configuration.setAllowCredentials(true); // 클라이언트에서 쿠키 허용

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
