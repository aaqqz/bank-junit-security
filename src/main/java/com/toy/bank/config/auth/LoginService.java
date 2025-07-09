package com.toy.bank.config.auth;

import com.toy.bank.domain.user.User;
import com.toy.bank.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티로 로그인 될때, 시큐리티가 loadUserByUsername 메서드를 호출한다.
    // 정상 처리 되면 UserDetails 객체를 반환한다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByName(username)
                .orElseThrow(() -> new InternalAuthenticationServiceException("인증 실패"));
        return new LoginUser(user);
    }
}
