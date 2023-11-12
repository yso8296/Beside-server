package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.common.annotation.LoggedInUserId;
import com.hackathon.beside.news.cardnews.presentation.response.CardNewsContentDto;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class CardNewsController {

    private final CardNewsService cardNewsService;

    @GetMapping("/cardnews/{newsId}")
    public CardNewsContentDto getCardNewsContents(
            @PathVariable @Positive long newsId
    ) {
        return cardNewsService.getCardNewsContents(newsId);
    }

    @GetMapping("/record/cardnews")
    public CardNewsRecordHasNextDto getChadNewsRecord(
            @LoggedInUserId Long userId,
            @PageableDefault(page = 0) Pageable pageable
    ) {
        CardNewsRecordHasNextDto newsRecord = cardNewsService.getNewsRecord(userId, pageable);
        return newsRecord;
    }

    @GetMapping("/cardnews/today")
    public TodayNewsDto getTodayNews(
            @LoggedInUserId Long userId
    ) {
        List<String> contentUrls = cardNewsService.getTodayNews(userId);
        return TodayNewsDto.toTodayNewsDto(contentUrls);
    }
}
