package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;


public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
