package com.hackathon.beside.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.OK;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final PasswordEncoder passwordEncoder;
    private final UserService userService;

    @GetMapping("/profile")  // 유저 프로필 조회
    public Profile getProfile(
            @RequestParam(value = "userId") Long userId
    ) {
        return userService.getUserProfile(userId);
    }

    @PostMapping("/users") // 회원가입
    public ResponseEntity<Void> join(
            @RequestBody JoinForm form
    ) {
        String encodedPassword = passwordEncoder.encode(form.getPassword());
        form.setPassword(encodedPassword);
        userService.join(form);

        return new ResponseEntity<>(OK);
    }

    @GetMapping("/users/account-name")  // 아이디 중복 체크
    public boolean checkDuplicateAccount(
            @RequestParam(name = "account") String account
    ) {
        return userService.checkAccountDuplicate(account);
    }
}
