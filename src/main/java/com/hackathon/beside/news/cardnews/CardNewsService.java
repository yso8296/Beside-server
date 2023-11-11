package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.common.entity.News;
import com.hackathon.beside.common.entity.NewsContent;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.news.cardnews.presentation.response.CardNewsContentDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CardNewsService {

    private final CardNewsRepository cardNewsRepository;

    public CardNewsContentDto getCardNewsContents(long newsId) {
        News news = cardNewsRepository.getCardNewsContents(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("요청하신 카드 뉴스를 찾을 수 없습니다."));

        List<String> newsContents = news.getNewsContents().stream()
                .map(NewsContent::getUrl)
                .toList();

        return new CardNewsContentDto(newsContents);
    }
}
