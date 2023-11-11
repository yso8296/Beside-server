package com.hackathon.beside.user;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")  // 유저 프로필 조회
    public Profile getProfile(
            @RequestParam(value = "userId") Long userId
    ) {
        Profile profile = userService.getUserProfile(userId);

        return profile;
    }

    @PostMapping("/users") // 회원가입
    public ResponseEntity<Void> join(
            @RequestBody JoinForm form
    ) {
        userService.join(form);
        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("/users/account-name")  // 아이디 중복 체크
    public boolean checkDuplicateAccount(
            @RequestParam(name = "account") String account
    ) {
        return userService.checkAccountDuplicate(account);
    }

    @GetMapping("/users/nickname")  // 닉네임 중복 체크
    public boolean checkDuplicateNickname(
            @RequestParam(name = "nickname") String nickname
    ) {
        return userService.checkNicknameDuplicate(nickname);
    }
}
