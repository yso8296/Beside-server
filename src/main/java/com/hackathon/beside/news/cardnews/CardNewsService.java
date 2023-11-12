package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.common.entity.*;
import com.hackathon.beside.common.exception.ResourceNotFoundException;
import com.hackathon.beside.common.newsUsersMapping.NewsUsersMappingRepository;
import com.hackathon.beside.news.cardnews.presentation.response.CardNewsContentDto;
import com.hackathon.beside.news.quiz.QuizRecordDto;
import com.hackathon.beside.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CardNewsService {

    private final CardNewsRepository cardNewsRepository;
    private final UserRepository userRepository;
    private final NewsUsersMappingRepository newsUsersMappingRepository;

    public CardNewsContentDto getCardNewsContents(long newsId) {
        News news = cardNewsRepository.getCardNewsContents(newsId)
                .orElseThrow(() -> new ResourceNotFoundException("요청하신 카드 뉴스를 찾을 수 없습니다."));

        List<String> newsContents = news.getNewsContents().stream()
                .map(NewsContent::getUrl)
                .toList();

        return new CardNewsContentDto(newsContents);
    }

    public CardNewsRecordHasNextDto getNewsRecord(Long userId, Pageable pageable) {
        int page = pageable.getPageNumber(); // page 위치에 있는 값은 0부터 시작한다.
        int pageLimit = 20; // 한페이지에 보여줄 글 개수

        User user = userRepository.findById(userId).orElseThrow();

        PageRequest pageRequest = PageRequest.of(page, pageLimit, Sort.by(Sort.Direction.DESC, "id"));
        Page<NewsUsersMapping> newsUsersMappings = newsUsersMappingRepository.findAllById(userId, pageRequest);
        boolean hasNext = newsUsersMappings.hasNext();

        List<CardNewsRecordDto> data = new ArrayList<>();

        for (NewsUsersMapping newsUsersMapping : newsUsersMappings) {
            if (isEqualUser(user, newsUsersMapping)) {
                News mappingNews = newsUsersMapping.getNews();
                List<String> urls = new ArrayList<>();

                List<NewsContent> newsContents = mappingNews.getNewsContents();
                for (NewsContent newsContent : newsContents) {
                    urls.add(newsContent.getUrl());
                }


                data.add(CardNewsRecordDto.toCardNewsRecordDto(mappingNews, urls));
            }
        }

        return CardNewsRecordHasNextDto.toQuizRecordHasNextDto(hasNext, data);
    }

    private static boolean isEqualUser(User user, NewsUsersMapping newsUsersMapping) {
        return Objects.equals(newsUsersMapping.getUser().getId(), user.getId());
    }

    public List<String> getTodayNews(Long userId) {
        Optional<News> todayNews = cardNewsRepository.getTodayNews();

        if (todayNews.isEmpty()) {
            throw new RuntimeException("Today's news is not available.");
        }

        List<NewsContent> newsContents = todayNews.get().getNewsContents();
        List<String> contentUrls = new ArrayList<>();

        for (NewsContent newsContent : newsContents) {
            contentUrls.add(newsContent.getUrl());
        }

        return contentUrls;
    }
}
