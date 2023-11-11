package com.hackathon.beside.auth;

import com.hackathon.beside.auth.presentation.request.FormLoginDto;
import com.hackathon.beside.auth.presentation.response.TokenDto;
import com.hackathon.beside.common.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Date;

@RequiredArgsConstructor
@Service
public class AuthService {

    private static final long REISSUE_STANDARD_TIME_7_DAYS = 1_000 * 60 * 60 * 24 * 7L;

    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final TokenProvider tokenProvider;
    private final JwtRepository redisRepository;

    public TokenDto formLogin(FormLoginDto formLoginDto) {
        final Authentication authentication = saveSecurityContext(formLoginDto);

        return createLoginResponse(authentication);
    }

    private Authentication saveSecurityContext(FormLoginDto formLoginDto) {
        final UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(formLoginDto.getAccount(), formLoginDto.getPassword());
        final Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        return authentication;
    }

    private TokenDto createLoginResponse(Authentication authentication) {
        String accessToken = tokenProvider.createAccessToken(authentication);
        String refreshToken = tokenProvider.createRefreshToken(authentication);

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenDto reissue(Long userId, Authentication authentication) {
        String currentRefreshToken = authentication.getCredentials().toString();

        String createdAccessToken = tokenProvider.createAccessToken(authentication);
        String createdRefreshToken = createRefreshToken(authentication, currentRefreshToken, userId);

        return TokenDto.builder()
                .accessToken(createdAccessToken)
                .refreshToken(createdRefreshToken)
                .build();
    }

    private String createRefreshToken(Authentication authentication, String currentRefreshToken, long userId) {
        Date currentTokenExpiredAt = tokenProvider.extractExpiredAt(currentRefreshToken);

        Date targetDay = new Date(System.currentTimeMillis() + REISSUE_STANDARD_TIME_7_DAYS);

        if (targetDay.after(currentTokenExpiredAt)) {
            redisRepository.saveBlackListToken(currentRefreshToken, userId, new Date());
            return tokenProvider.createRefreshToken(authentication);
        }

        return null;
    }
}
