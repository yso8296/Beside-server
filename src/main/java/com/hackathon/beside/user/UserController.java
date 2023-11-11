package com.hackathon.beside.user;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class UserController {

    private final UserService userService;

    @GetMapping("/profile")  // 유저 프로필 조회
    public UserProfileDto getProfile(
            Long userId
    ) {
        UserProfileDto userProfileDto = userService.getUserProfile(userId);

        return userProfileDto;
    }
}
