package com.hackathon.beside.news.quiz.presentation.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@Getter
@NoArgsConstructor
public class Answer {

    private long optionId;
    private long questionId;
}
