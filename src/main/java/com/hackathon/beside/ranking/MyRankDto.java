package com.hackathon.beside.ranking;

import com.hackathon.beside.common.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyRankDto {

    private int rank;
    private String nickname;
    private String schoolName;
    private float score;

    public static MyRankDto toMyRankDto(User user, int rank, float score) {
        return MyRankDto.builder()
                .rank(rank)
                .nickname(user.getNickname())
                .schoolName(user.getSchool().getName())
                .score(score)
                .build();
    }
}
