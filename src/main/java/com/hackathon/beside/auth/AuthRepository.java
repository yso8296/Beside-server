package com.hackathon.beside.auth;

import com.hackathon.beside.common.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthRepository extends JpaRepository<User, Long> {

    User findByAccount(String account);
}
