package com.example.jspboard2.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@NoArgsConstructor
public class EmailAuthRequest{

    @NotEmpty(message = "이메일을 입력해주세요")
    public String email;
    public String title;
    public String message;
}
