package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.entity.Quiz;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.news.cardnews.presentation.response.QuizDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class QuizService {

    private final QuizRepository quizRepository;

    //todo 풀었는 문제인 경우 해설과, 선택한 보기 표시할 수 있도록 구현.
    public QuizDto getQuizById(long quizId) {
        Quiz quiz = quizRepository.findById(quizId)
                .orElseThrow(() -> new ResourceNotFoundException("존재 하지 않는 퀴즈 입니다."));

        return QuizDto.toQuizDto(quiz);
    }
}
