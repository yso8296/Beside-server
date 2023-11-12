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
    private long selectedOptionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private long answerOptionId;
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

    public static List<QuestionDto> toQuestions(List<Question> questions) {
        return questions.stream()
                .map(question -> new QuestionDto(question.getId(), question.getContent(), question.getQuizOptions()))
                .toList();
    }

    public static List<QuestionDto> toWrongAnswerNote(List<Question> questions, List<QuizOptionUserMapping> quizOptionUserMappings,long userId) {
        long answerOptionId = extractAnswerOptionId(quizOptionUserMappings);
        long selectedOptionId = extractSelectedOptionId(quizOptionUserMappings,userId);

        return convertToQuestionDto(questions, selectedOptionId, answerOptionId);
    }

    private static List<QuestionDto> convertToQuestionDto(List<Question> questions, long selectedOptionId, long answerOptionId) {
        return questions.stream()
                .map(question -> QuestionDto.builder()
                        .id(question.getId())
                        .content(question.getContent())
                        .quizOptions(question.getQuizOptions())
                        .explanation(question.getExplanation())
                        .selectedOptionId(selectedOptionId)
                        .answerOptionId(answerOptionId)
                        .build())
                .toList();
    }

    private static Long extractSelectedOptionId(List<QuizOptionUserMapping> quizOptionUserMappings, long userId) {
        return quizOptionUserMappings.stream()
                .filter(quizOptionUserMapping -> quizOptionUserMapping.getUser().getId() == 1)
                .findAny()
                .map(QuizOptionUserMapping::getId)
                .orElseThrow(() -> new ResourceNotFoundException("유저가 푼 퀴즈가 아닙니다."));
    }

    private static Long extractAnswerOptionId(List<QuizOptionUserMapping> quizOptionUserMappings) {
        return quizOptionUserMappings.stream()
                .filter(quizOptionUserMapping -> quizOptionUserMapping.getQuizOption().isAnswer())
                .findAny()
                .map(QuizOptionUserMapping::getId)
                .orElseThrow(() -> new ResourceNotFoundException("유저가 푼 퀴즈가 아닙니다."));
    }
}
