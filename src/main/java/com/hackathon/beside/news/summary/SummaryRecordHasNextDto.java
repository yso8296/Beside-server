package com.hackathon.beside.news.summary;

import com.hackathon.beside.news.quiz.QuizRecordDto;
import com.hackathon.beside.news.quiz.QuizRecordHasNextDto;
import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SummaryRecordHasNextDto {

    private boolean hasNext;
    private List<SummaryRecordDto> summaryRecordDtos;

    public static SummaryRecordHasNextDto toSummaryRecordHasNextDto(boolean hasNext, List<SummaryRecordDto> summaries) {
        return SummaryRecordHasNextDto.builder()
                .hasNext(hasNext)
                .summaryRecordDtos(summaries)
                .build();
    }
}
