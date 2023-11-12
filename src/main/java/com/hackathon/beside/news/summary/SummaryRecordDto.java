package com.hackathon.beside.news.summary;

import com.hackathon.beside.common.entity.Summary;
import lombok.*;

import java.time.LocalDate;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SummaryRecordDto {

    private Long summaryId;
    private int takenTime;
    private LocalDate publishedDate;
    private String subject;
    private String content;

    public static SummaryRecordDto toSummaryRecordDto(Summary summary) {
        return SummaryRecordDto.builder()
                .summaryId(summary.getId())
                .takenTime(summary.getTakenTime())
                .publishedDate(summary.getPublishedDate())
                .subject(summary.getSubject())
                .content(summary.getContent())
                .build();
    }
}
