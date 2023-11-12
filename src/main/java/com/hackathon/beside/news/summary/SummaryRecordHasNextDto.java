package com.hackathon.beside.news.summary;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SummaryRecordHasNextDto {

    private boolean hasNext;
    private List<SummaryRecordDto> data;

    public static SummaryRecordHasNextDto toSummaryRecordHasNextDto(boolean hasNext, List<SummaryRecordDto> summaries) {
        return SummaryRecordHasNextDto.builder()
                .hasNext(hasNext)
                .data(summaries)
                .build();
    }
}
