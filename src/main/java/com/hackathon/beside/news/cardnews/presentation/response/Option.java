package com.hackathon.beside.news.cardnews.presentation.response;

import com.hackathon.beside.common.entity.QuizOption;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class Option {

    private long optionId;
    private String content;

    public Option(long optionId, String content) {
        this.optionId = optionId;
        this.content = content;
    }

    public static List<Option> toOptions(List<QuizOption> quizOptions) {
        return quizOptions.stream()
                .map(option -> new Option(option.getId(), option.getContent()))
                .toList();
    }
}
