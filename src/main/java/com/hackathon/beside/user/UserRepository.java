package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByAccount(String account);

    boolean existsByNickname(String nickname);

    User findByAccount(String account);
}
