package com.hackathon.beside.news.cardnews;

import com.hackathon.beside.news.quiz.QuizRecordDto;
import com.hackathon.beside.news.quiz.QuizRecordHasNextDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class CardNewsRecordHasNextDto {

    private boolean hasNext;
    private List<CardNewsRecordDto> cardNewsRecordDtos;

    public static CardNewsRecordHasNextDto toQuizRecordHasNextDto(boolean hasNext, List<CardNewsRecordDto> cardNewsRecordDtos) {
        return CardNewsRecordHasNextDto.builder()
                .hasNext(hasNext)
                .cardNewsRecordDtos(cardNewsRecordDtos)
                .build();
    }
}
