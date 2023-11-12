package com.hackathon.beside.news.cardnews;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TodayNewsDto {

    private List<String> contentUrls;

    public static TodayNewsDto toTodayNewsDto(List<String> contentUrls) {
        return TodayNewsDto.builder()
                .contentUrls(contentUrls)
                .build();
    }
}
