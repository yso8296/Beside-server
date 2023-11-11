package com.hackathon.beside.user;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserProfileDto {
    private String nickname;
    private String schoolName;
    private String interest;
    private Long readNewsCount;
    private Long solvedQuizCount;
    private float correctRate;
}
