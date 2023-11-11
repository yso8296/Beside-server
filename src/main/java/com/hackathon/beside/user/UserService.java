package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.Interest;
import com.hackathon.beside.common.entity.User;
import com.hackathon.beside.interest.InterestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class UserService {

    private final UserRepository userRepository;
    private final InterestRepository interestRepository;

    public Profile getUserProfile(Long userId) {
        User user = userRepository.findById(userId).orElseThrow();

        Profile profile = Profile.toProfile(user);

        return profile;
    }

    public void join(JoinForm form) {
        Interest interest = Interest.toEntity(form.getInterest());
        User user = User.toEntity(form, interest);

        interestRepository.save(interest);
        userRepository.save(user);
    }
}
