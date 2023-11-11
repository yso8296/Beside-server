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

    public UserProfileDto getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        UserProfileDto profile = new UserProfileDto();
        profile.setNickname(user.getNickname());
        profile.setSchoolName(user.getSchool().getName());
        profile.setInterest(user.getInterest().getName());
        profile.setReadNewsCount(user.getReadNewsCount());
        profile.setSolvedQuizCount(user.getEnteredQuizCount());
        profile.setCorrectRate(user.getCorrectRate());

        return profile;
    }

}
