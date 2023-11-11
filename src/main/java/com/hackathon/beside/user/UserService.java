package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;

    public Profile getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        Profile profile = Profile.toProfile(user);

        return profile;
    }

}
