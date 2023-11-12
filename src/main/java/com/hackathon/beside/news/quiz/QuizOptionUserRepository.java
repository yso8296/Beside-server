package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.QuizOptionUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface QuizOptionUserRepository extends JpaRepository<QuizOptionUserMapping, Long> {

    List<QuizOptionUserMapping> findAllByQuizIdAndUserId(Long quizId, Long userId);
}
