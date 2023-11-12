package com.hackathon.beside.ranking;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class SchoolRankDto {

    private int rank;
    private String schoolName;
    private float score;

    public static SchoolRankDto toSchoolRankDto(int rank, String schoolName, float score) {
        return SchoolRankDto.builder()
                .rank(rank)
                .schoolName(schoolName)
                .score(score)
                .build();
    }
}
