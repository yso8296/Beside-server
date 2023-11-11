package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;


public interface QuizRepository extends JpaRepository<Quiz, Long> {
}
