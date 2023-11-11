package com.hackathon.beside.user;

import lombok.*;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class JoinForm {

    private String account;
    private String password;
    private String nickname;
    private String schoolName;
    private String interest;

    public void setPassword(String password) {
        this.password = password;
    }
}
