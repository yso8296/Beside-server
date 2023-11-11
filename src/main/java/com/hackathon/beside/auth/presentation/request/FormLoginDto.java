package com.hackathon.beside.auth.presentation.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class FormLoginDto {

    @NotBlank
    private String account;

    @NotBlank
    private String password;
}
