package com.hackathon.beside.interest;

import com.hackathon.beside.common.entity.Interest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InterestRepository extends JpaRepository<Interest, Long> {
}
