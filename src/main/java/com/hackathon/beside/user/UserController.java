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
}
