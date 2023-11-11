package com.hackathon.beside.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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


}
