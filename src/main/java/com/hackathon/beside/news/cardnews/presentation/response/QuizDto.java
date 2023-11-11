package com.hackathon.beside.news.cardnews.presentation.response;

import com.hackathon.beside.common.entity.Quiz;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuizDto {

    private long quizId;
    private String quizName;
    private List<QuestionDto> questions;

    public static QuizDto toQuizDto(Quiz quiz) {
        List<QuestionDto> questions = QuestionDto.toQuestions(quiz.getQuestions());

        return QuizDto.builder()
                .quizId(quiz.getId())
                .quizName(quiz.getSubject())
                .questions(questions)
                .build();


    }

    @Builder
    public QuizDto(long quizId, String quizName, List<QuestionDto> questions) {
        this.quizId = quizId;
        this.quizName = quizName;
        this.questions = questions;
    }
}
