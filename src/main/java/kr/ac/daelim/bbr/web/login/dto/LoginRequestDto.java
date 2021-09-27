package kr.ac.daelim.bbr.web.login.dto;

import lombok.*;

import javax.validation.constraints.NotEmpty;

@Getter
public class LoginRequestDto {

    @NotEmpty private String username;
    @NotEmpty private String password;

    @Builder
    public LoginRequestDto(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
