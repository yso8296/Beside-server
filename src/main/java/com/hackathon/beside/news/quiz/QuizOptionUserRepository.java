package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.QuizOptionUserMapping;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface QuizOptionUserRepository extends JpaRepository<QuizOptionUserMapping, Long> {

    @Query("select qoum from QuizOptionUserMapping qoum where qoum.user.id =:userId and qoum.quizOption.question.id in (:questionIds)")
    List<QuizOptionUserMapping> findAllUserChoice(Long userId,List<Long> questionIds);
}
