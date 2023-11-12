package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Question;
import com.hackathon.beside.common.entity.Quiz;
import com.hackathon.beside.common.entity.QuizOptionUserMapping;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.news.cardnews.presentation.response.QuizDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;
    private final QuizOptionUserRepository quizOptionUserRepository;

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
        
        List<QuizOptionUserMapping> quizOptionUserMappings = quizOptionUserRepository.findAllUserChoice(userId, questionIds);

        List<QuizOptionUserMapping> wrongAnswerQuestions = quizOptionUserMappings.stream()
                .filter(quizOptionUserMapping -> !quizOptionUserMapping.getQuizOption().isAnswer())
                .toList();


        return QuizDto.toWrongAnswerNote(quiz, wrongAnswerQuestions, userId);
    }
}
