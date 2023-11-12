package com.hackathon.beside.ranking;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalSchoolDto {

    private SchoolRankDto myRanking;
    private List<SchoolRankDto> rankingUsers;

    public static TotalSchoolDto toTotalRankDto(SchoolRankDto me, List<SchoolRankDto> myRankDtos) {
        return TotalSchoolDto.builder()
                .myRanking(me)
                .rankingUsers(myRankDtos)
                .build();
    }
}
