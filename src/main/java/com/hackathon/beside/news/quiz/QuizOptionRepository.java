package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.QuizOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface QuizOptionRepository extends JpaRepository<QuizOption, Long> {

    @Query("select option from QuizOption option join fetch option.question question where question.id =:questionId and option.isAnswer=true")
    QuizOption findAnswerOptionByQuestionId(@Param("questionId") Long questionId);

    @Query("select option from QuizOption option where option.question.id in (:questionIds) and option.isAnswer = true")
    List<QuizOption> findAllInQuestionIds(@Param("questionIds") List<Long> questionIds);

    @Query("select option from QuizOption  option where option.question.id =:questionId and option.id=:optionId")
    QuizOption findByQuestionIdAndOptionId(@Param("questionId") long questionId, @Param("optionId") long optionId);
}
