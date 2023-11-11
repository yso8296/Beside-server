package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.news.cardnews.presentation.response.CardNewsContentDto;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
}
