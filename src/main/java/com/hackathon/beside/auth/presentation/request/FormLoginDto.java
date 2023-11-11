package com.hackathon.beside.auth.presentation.request;

import com.hackathon.beside.user.JoinForm;
import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class FormLoginDto {

    @NotBlank
    private String account;

    @NotBlank
    private String password;

    public static FormLoginDto from(JoinForm joinRequest) {
        return builder()
                .account(joinRequest.getAccount())
                .password(joinRequest.getPassword())
                .build();
    }

    @Builder
    public FormLoginDto(String account, String password) {
        this.account = account;
        this.password = password;
    }
}
