package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Question;
import com.hackathon.beside.common.entity.Quiz;
import com.hackathon.beside.common.entity.QuizOption;
import com.hackathon.beside.common.entity.QuizOptionUserMapping;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.news.cardnews.presentation.response.QuestionDto;
import com.hackathon.beside.news.cardnews.presentation.response.QuizDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizOptionUserRepository quizOptionUserRepository;
    private final QuizOptionRepository quizOptionRepository;

    //todo 풀었는 문제인 경우 해설과, 선택한 보기 표시할 수 있도록 구현.
    public QuizDto getTodayQuiz() {
        Quiz quiz = quizRepository.getTodayQuiz()
                .orElseThrow(() -> new ResourceNotFoundException("존재 하지 않는 퀴즈 입니다."));

        return QuizDto.toTodayQuiz(quiz);
    }

    public QuizDto getQuizById(long quizId, Long userId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("존재 하지 않는 퀴즈 입니다."));
        List<Long> questionIds = quiz.getQuestions().stream()
                .map(Question::getId)
                .toList();

        List<QuizOptionUserMapping> quizOptionUserMappings = quizOptionUserRepository.findByUserChoices(userId, questionIds);
        List<QuizOptionUserMapping> wrongAnswerQuestions = quizOptionUserMappings.stream()
                .filter(quizOptionUserMapping -> !quizOptionUserMapping.getQuizOption().isAnswer())
                .toList();

        List<Long> wrongAnswerQuestionIds = wrongAnswerQuestions.stream()
                .map(QuizOptionUserMapping::getId)
                .toList();

        Map<Long,Question> wrongQuestionMap = quiz.getQuestions()
                .stream()
                .filter(question -> wrongAnswerQuestionIds.contains(question.getId()))
                .collect(Collectors.toMap(
                        Question::getId,
                        question -> question
                ));

        Map<Long, QuizOptionUserMapping> quizOptionUserMap = wrongAnswerQuestionIds.stream()
                .collect(Collectors.toMap(
                        questionId -> questionId,
                        questionId -> quizOptionUserRepository.findByUserIdAndQuestionId(userId, questionId))
                );

        Map<Long, QuizOption> answerOptionMap = wrongAnswerQuestionIds.stream()
                .collect(Collectors.toMap(
                        questionId -> questionId,
                        quizOptionRepository::findAnswerOptionByQuestionId
                ));

        List<QuestionDto> questionDtos = new ArrayList<>();
        wrongAnswerQuestionIds.forEach(
                questionId -> questionDtos.add(
                        new QuestionDto(
                                wrongQuestionMap.get(questionId),
                                quizOptionUserMap.get(questionId),
                                answerOptionMap.get(questionId)
                        )));

        return new QuizDto(quiz, questionDtos);
    }
}
