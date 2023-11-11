package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.User;
import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Profile {

    private String nickname;
    private String schoolName;
    private String interest;
    private Long readNewsCount;
    private Long solvedQuizCount;
    private float correctRate;

    public static Profile toProfile(User user) {
        return Profile.builder()
                .nickname(user.getNickname())
                .schoolName(user.getSchool().getName())
                .interest(user.getInterest().getName())
                .readNewsCount(user.getReadNewsCount())
                .solvedQuizCount(user.getEnteredQuizCount())
                .correctRate(user.getCorrectRate())
                .build();
    }
}
