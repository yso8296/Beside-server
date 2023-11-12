package com.hackathon.beside.ranking;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalRankDto {

    private MyRankDto myRanking;
    private List<MyRankDto> rankingUsers;

    public static TotalRankDto toTotalRankDto(MyRankDto me, List<MyRankDto> myRankDtos) {
        return TotalRankDto.builder()
                .myRanking(me)
                .rankingUsers(myRankDtos)
                .build();
    }
}
