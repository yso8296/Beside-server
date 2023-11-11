package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.Interest;
import com.hackathon.beside.common.entity.School;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.interest.InterestRepository;
import com.hackathon.beside.school.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final SchoolRepository schoolRepository;

    public Profile getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        Profile profile = Profile.toProfile(user);

        return profile;
    }

    public void join(JoinForm form) {
        Interest interest = Interest.toEntity(form.getInterest());
        School school = School.toEntity(form.getSchoolName());

        schoolRepository.save(school);

        User user = User.toEntity(form);
        user.setInterest(interest);
        user.setSchool(school);

        userRepository.save(user);
    }
}
