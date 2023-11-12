package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuizOptionRepository extends JpaRepository<QuizOption, Long> {

    @Query("select option from QuizOption option join fetch option.question question where question.id =:questionId and option.isAnswer=true")
    QuizOption findAnswerOptionByQuestionId(@Param("questionId") Long questionId);
}
