package com.hackathon.beside.news.quiz;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class QuizRecordHasNextDto {

    private boolean hasNext;
    private List<QuizRecordDto> data;

    public static QuizRecordHasNextDto toQuizRecordHasNextDto(boolean hasNext, List<QuizRecordDto> data) {
        return QuizRecordHasNextDto.builder()
                .hasNext(hasNext)
                .data(data)
                .build();

    }
}
