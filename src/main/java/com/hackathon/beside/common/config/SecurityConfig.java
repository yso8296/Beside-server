package com.hackathon.beside.common.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackathon.beside.auth.JwtRepository;
import com.hackathon.beside.common.jwt.AddBlackListLogoutHandler;
import com.hackathon.beside.common.jwt.JwtAccessDeniedHandler;
import com.hackathon.beside.common.jwt.JwtAuthenticationEntryPoint;
import com.hackathon.beside.common.jwt.TokenProvider;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer.FrameOptionsConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutHandler;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final TokenProvider tokenProvider;
    private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;
    private final ObjectMapper objectMapper;
    private final JwtRepository redisRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public LogoutHandler addBlackListLogoutHandler() {
        return new AddBlackListLogoutHandler(objectMapper, tokenProvider, redisRepository);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)

                .exceptionHandling(handler -> handler
                        .authenticationEntryPoint(jwtAuthenticationEntryPoint)
                        .accessDeniedHandler(jwtAccessDeniedHandler))

                .headers(headers -> headers.frameOptions(FrameOptionsConfig::sameOrigin))

                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                .authorizeHttpRequests(authorize ->
                        authorize.requestMatchers("/users", "/users/account-name", "/login/form", "/swagger-ui/**", "/v3/api-docs/**").permitAll()
                                .anyRequest().authenticated()
                )

                .logout(logout -> logout.addLogoutHandler(addBlackListLogoutHandler()))
                .logout(logout -> logout.logoutSuccessUrl("/logout/success").permitAll())

                .apply(new JwtSecurityConfig(tokenProvider, redisRepository));

        return http.build();
    }
}
