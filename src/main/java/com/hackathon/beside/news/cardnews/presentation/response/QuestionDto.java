package com.hackathon.beside.news.cardnews.presentation.response;

import com.hackathon.beside.common.entity.Question;
import com.hackathon.beside.common.entity.QuizOption;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionDto {

    private long questionId;
    private String questionName;
    private List<Option> options;

    public QuestionDto(Long id, String content, List<QuizOption> quizOptions) {
        this.questionId=id;
        this.questionName = content;
        this.options = Option.toOptions(quizOptions);
    }

    public static List<QuestionDto> toQuestions(List<Question> questions) {
        return questions.stream()
                .map(question -> new QuestionDto(question.getId(), question.getContent(), question.getQuizOptions()))
                .toList();
    }

}
