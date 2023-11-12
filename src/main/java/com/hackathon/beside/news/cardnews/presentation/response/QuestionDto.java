package com.hackathon.beside.news.cardnews.presentation.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hackathon.beside.common.entity.Question;
import com.hackathon.beside.common.entity.QuizOption;
import com.hackathon.beside.common.entity.QuizOptionUserMapping;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionDto {

    private long questionId;
    private String questionName;
    private List<Option> options;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long selectedOptionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long answerOptionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String explanation;

    public QuestionDto(Long id, String content, List<QuizOption> quizOptions) {
        this.questionId = id;
        this.questionName = content;
        this.options = Option.toOptions(quizOptions);
    }

    @Builder
    private QuestionDto(Long id, String content, List<QuizOption> quizOptions, String explanation, long selectedOptionId, long answerOptionId) {
        this(id, content, quizOptions);
        this.explanation = explanation;
    }

    public QuestionDto(Question question, QuizOptionUserMapping quizOptionUserMapping, QuizOption answerOption) {
        this.questionId=question.getId();
        this.questionName = question.getContent();
        this.selectedOptionId = quizOptionUserMapping.getQuizOption().getId();
        this.answerOptionId = answerOption.getId();
        this.explanation = question.getExplanation();
        this.options = Option.toOptions(question.getQuizOptions());
    }

    public static List<QuestionDto> toQuestions(List<Question> questions) {
        return questions.stream()
                .map(question -> new QuestionDto(question.getId(), question.getContent(), question.getQuizOptions()))
                .toList();
    }
}
