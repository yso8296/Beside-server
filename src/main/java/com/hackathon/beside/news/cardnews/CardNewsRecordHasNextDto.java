package com.hackathon.beside.news.cardnews;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardNewsRecordHasNextDto {

    private boolean hasNext;
    private List<CardNewsRecordDto> data;

    public static CardNewsRecordHasNextDto toQuizRecordHasNextDto(boolean hasNext, List<CardNewsRecordDto> data) {
        return CardNewsRecordHasNextDto.builder()
                .hasNext(hasNext)
                .data(data)
                .build();
    }
}
