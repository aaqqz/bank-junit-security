package com.toy.bank.config.jwt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.toy.bank.common.dto.ApiResponse;
import com.toy.bank.config.auth.LoginDto;
import com.toy.bank.config.auth.LoginUser;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
        super(authenticationManager);
        setFilterProcessesUrl("/api/login");
        this.authenticationManager = authenticationManager;
    }

    // todo filter 등록
    public class CustomSecurityFilterManager extends AbstractHttpConfigurer<CustomSecurityFilterManager, HttpSecurity> {
        @Override
        public void configure(HttpSecurity builder) throws Exception {}
    }

    // POST /login 요청을 처리하는 메서드
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            // JSON 요청에서 사용자 이름과 비밀번호를 추출
            LoginDto.LoginRequest loginRequest = objectMapper.readValue(request.getInputStream(), LoginDto.LoginRequest.class);
            String username = loginRequest.getName();
            String password = loginRequest.getPassword();

            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(username, password);

            // UserDetailsService 의 loadUserByUsername 메서드를 호출하여 사용자 인증을 수행
            Authentication authentication = authenticationManager.authenticate(authenticationToken);

            return authentication;
        } catch (Exception e) {
            // securityConfig 의 authenticationEntryPoint 에서 처리하도록 예외를 던짐
            throw new InternalAuthenticationServiceException(e.getMessage(), e);
        }
    }


    // 인증 성공 후 호출되는 메서드 ( attemptAuthentication 정상 return 되면 )
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        LoginUser loginUser = (LoginUser) authResult.getPrincipal();
        String jwtToken = JwtProcess.createToken(loginUser);
        response.addHeader("Authorization", "Bearer " + jwtToken);

        LoginDto.LoginResponse loginResponse = LoginDto.LoginResponse.from(loginUser.getUser());
        ApiResponse.success(loginResponse);
    }
}
