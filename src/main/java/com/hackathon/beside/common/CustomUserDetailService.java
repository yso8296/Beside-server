package com.hackathon.beside.common;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.hackathon.beside.auth.AuthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomUserDetailService implements UserDetailsService {

    private final AuthRepository authRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        com.hackathon.beside.common.entity.User user = authRepository.getReferenceById(Long.parseLong(username));
        // todo EncodedPassword가 어떻게 나올 수 있지..?
        String encodedPassword = passwordEncoder.encode(user.getPassword());

        List<GrantedAuthority> grantedAuthorities = List.of(new SimpleGrantedAuthority(user.getAuthority().name()));
        // todo 왜 권한정보를 List로 받아야 하게 만들었을까..? 권한을 계층 구조로 만들어야하나..?

        return new User(String.valueOf(user.getId()), user.getPassword(), grantedAuthorities);
    }
}
