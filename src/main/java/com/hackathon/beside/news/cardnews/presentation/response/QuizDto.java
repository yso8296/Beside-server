package com.hackathon.beside.news.cardnews.presentation.response;

import com.hackathon.beside.common.entity.Quiz;
import com.hackathon.beside.common.entity.QuizOptionUserMapping;
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


    public static QuizDto toTodayQuiz(Quiz quiz) {
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

    public QuizDto(Quiz quiz, List<QuestionDto> questionDtos) {
        this.quizId = quiz.getId();
        this.quizName = quiz.getSubject();
        this.questions = questionDtos;
    }
}
