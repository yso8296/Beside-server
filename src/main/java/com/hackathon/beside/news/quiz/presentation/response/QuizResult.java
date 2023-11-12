package com.hackathon.beside.news.quiz.presentation.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class QuizResult {

    private String nickname;
    private int correctAnswerCount;
    private int totalQuestionCount;
}
