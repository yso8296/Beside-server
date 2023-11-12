package com.hackathon.beside.news.quiz;

import com.hackathon.beside.common.annotation.LoggedInUserId;
import com.hackathon.beside.news.cardnews.presentation.response.QuizDto;
import com.hackathon.beside.news.quiz.presentation.request.SubmitQuiz;
import com.hackathon.beside.news.quiz.presentation.response.QuizResult;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

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
    ) {
        return quizService.getQuizById(quizId, userId);
    }

    @GetMapping("/record/quiz")
    public QuizRecordHasNextDto quizRecord(
            @LoggedInUserId Long userId,
            @PageableDefault(page = 0) Pageable pageable
    ) {
        QuizRecordHasNextDto quizRecordHasNextDto = quizService.quizRecord(userId, pageable);

        return quizRecordHasNextDto;
    }

    @PostMapping("/quiz/submit")
    public QuizResult submitQuiz(
            @LoggedInUserId Long userId,
            @RequestBody SubmitQuiz submitQuiz
    ) {

        System.out.println("userId = " + userId);
        System.out.println("submitQuiz = " + submitQuiz);

        return quizService.submitQuiz(userId, submitQuiz);
    }
}
