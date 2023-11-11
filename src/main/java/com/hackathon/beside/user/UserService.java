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

    @Transactional
    public void join(JoinForm form) {
        Interest interest = Interest.toEntity(form.getInterest());
        User user = User.toEntity(form);

        School school = schoolRepository.findByName(form.getSchoolName());
        if (school == null) {
            school = School.toEntity(form.getSchoolName());
            schoolRepository.save(school);
        }

        user.setSchool(school);
        user.setInterest(interest);

        userRepository.save(user);
    }
}
