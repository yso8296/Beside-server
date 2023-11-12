package com.hackathon.beside.ranking;

import lombok.*;

import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class TotalRankDto {

    private MyRankDto me;
    private List<MyRankDto> myRankDtos;

    public static TotalRankDto toTotalRankDto(MyRankDto me, List<MyRankDto> myRankDtos) {
        return TotalRankDto.builder()
                .me(me)
                .myRankDtos(myRankDtos)
                .build();
    }
}
