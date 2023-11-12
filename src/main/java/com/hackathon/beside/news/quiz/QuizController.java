package com.hackathon.beside.news.quiz;

import com.hackathon.beside.news.cardnews.presentation.response.QuizDto;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class QuizController {

    private final QuizService quizService;

    @GetMapping("/quiz/{quizId}")
    public QuizDto getQuizById(
            @PathVariable("quizId") @Positive long quizId
    ) {
        return quizService.getQuizById(quizId);
    }

    @GetMapping("/record/quiz")
    public QuizRecordHasNextDto quizRecord(
            @RequestParam("userId") Long userId,
            @PageableDefault(page = 0) Pageable pageable
    ) {
        QuizRecordHasNextDto quizRecordHasNextDto = quizService.quizRecord(userId, pageable);

        return quizRecordHasNextDto;
    }
}
