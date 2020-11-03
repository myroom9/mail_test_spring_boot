package com.example.mail_sender;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MailServiceTest {
    @Autowired
    private MailService mailService;

    @Test
    @DisplayName("이메일 전소오 테스트")
    void sendMailTest() {
        String to = "myroom9@naver.com"; // TODO: 준영님 메일로 수정 필요
        String subject = "메일 테스트";
        String content = "테스트 매일 입니다.@토ㅓ!()*ㅓ" +
                "ㅎㅎㅎㅎ";

        mailService.sendMail(to, subject, content);
    }
}