package com.hackathon.beside.user;

import com.hackathon.beside.common.entity.Interest;
import com.hackathon.beside.common.entity.Term;
import lombok.*;

import java.util.List;

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


}
