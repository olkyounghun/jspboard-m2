package com.example.jspboard2.controller;

import com.example.jspboard2.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MailController {

    @Autowired
    MailService mailService;

    //127.0.0.1:8080/ROOT/api/mail/confirm.json?email
    @PostMapping(value = "code/mailConfirm")
    @ResponseBody
    public String mailConfirm(@RequestParam(name = "email") String email) throws Exception{
        String code = mailService.sendSimpleMessage(email);
        System.out.println("사용자에게 발송한 인증코드 ==> " + code);

        return code;
    }

}