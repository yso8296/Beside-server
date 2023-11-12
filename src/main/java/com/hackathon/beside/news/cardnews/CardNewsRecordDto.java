package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.common.entity.News;
import com.hackathon.beside.common.entity.NewsContent;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardNewsRecordDto {

    private Long newsId;
    private int takenTime;
    private LocalDate publishedDate;
    private List<String> urls;

    public static CardNewsRecordDto toCardNewsRecordDto(News news, List<String> urls) {
        return CardNewsRecordDto.builder()
                .newsId(news.getId())
                .takenTime(news.getTakenTime())
                .publishedDate(news.getPublishedDate())
                .urls(urls)
                .build();
    }
}
