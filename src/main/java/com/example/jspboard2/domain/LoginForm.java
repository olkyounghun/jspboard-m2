package com.example.jspboard2.domain;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class LoginForm {

     // DB에 따로 작성하지않더라도 임시적으로 적어서 사용이 가능한가??

    @NotEmpty
    private String loginId;

    @NotEmpty
    private String password;
}
