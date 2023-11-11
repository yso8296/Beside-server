package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public User getProfile(long userId) {
        User retreivedUser = null;
        try {
            Optional<User> user = userRepository.findById(userId);

            retreivedUser = user.get();
            System.out.println(retreivedUser);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return retreivedUser;
    }
}
