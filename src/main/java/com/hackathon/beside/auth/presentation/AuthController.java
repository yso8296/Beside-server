package com.hackathon.beside.auth.presentation;

import com.hackathon.beside.auth.AuthService;
import com.hackathon.beside.auth.presentation.request.FormLoginDto;
import com.hackathon.beside.auth.presentation.response.TokenDto;
import com.hackathon.beside.common.annotation.LoggedInUserId;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RequiredArgsConstructor
@RestController
public class AuthController {

    private final AuthService authService;

    @PostMapping("/login/form")
    public TokenDto formLogin(@RequestBody FormLoginDto formLoginRequest) {

        return authService.formLogin(formLoginRequest);
    }

    @PostMapping("/reissue")
    public TokenDto reissue(
            @LoggedInUserId Long userId,
            Authentication authentication
    ) {

        return authService.reissue(userId,authentication);
    }

    @GetMapping("/logout/success")
    public boolean returnLogoutResponse() {
        return true;
    }
}
