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

    public static QuizDto toWrongAnswerNote(Quiz quiz, List<QuizOptionUserMapping> quizOptionUserMapping, long userId) {
        List<QuestionDto> questions = QuestionDto.toWrongAnswerNote(quiz.getQuestions(), quizOptionUserMapping, userId);

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
