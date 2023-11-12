package com.hackathon.beside.news.quiz.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor
public class SubmitQuiz {

    private long quizId;
    private List<Answer> answers;
}
