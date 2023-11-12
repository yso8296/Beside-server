package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.Interest;
import com.hackathon.beside.common.entity.School;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.common.exception.DuplicateAccount;
import com.hackathon.beside.common.exception.DuplicateNickname;
import com.hackathon.beside.interest.InterestRepository;
import com.hackathon.beside.school.SchoolRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

import static java.util.Objects.isNull;

@RequiredArgsConstructor
@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
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

        boolean isDuplicateAccount = checkAccountDuplicate(form.getAccount());
        if (isDuplicateAccount) {
            throw new DuplicateAccount("이미 존재 하는 아이디 입니다.");
        }

        boolean isDuplicateNickname = checkNicknameDuplicate(form.getNickname());
        if (isDuplicateNickname) {
            throw new DuplicateNickname("이미 존재하는 닉네임 입니다.");
        }

        School school = schoolRepository.findByName(form.getSchoolName());
        if (isNull(school)) {
            school = School.toEntity(form.getSchoolName());
            schoolRepository.save(school);
        }

        user.setSchool(school);
        user.setInterest(interest);

        userRepository.save(user);
    }

    public boolean checkAccountDuplicate(String account) {
        return userRepository.existsByAccount(account);
    }

    public boolean checkNicknameDuplicate(String nickname) {
        return userRepository.existsByNickname(nickname);
    }
}
