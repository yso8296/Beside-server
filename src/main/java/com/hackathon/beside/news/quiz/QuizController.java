package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.annotation.LoggedInUserId;
import com.hackathon.beside.news.cardnews.presentation.response.QuizDto;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz/today")
    public QuizDto getTodayQuiz() {
        return quizService.getTodayQuiz();
    }

    @GetMapping("/quiz/{quizId}")
    public QuizDto getTodayQuiz(
            @LoggedInUserId Long userId,
            @PathVariable("quizId") @Positive long quizId
    ){
        return quizService.getQuizById(quizId,userId);
    }
}
