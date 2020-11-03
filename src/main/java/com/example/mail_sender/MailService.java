package com.example.mail_sender;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

// @see: https://gigas-blog.tistory.com/121
@Slf4j
@Service
@RequiredArgsConstructor
public class MailService {
    private final JavaMailSender javaMailSender;
    private final TemplateEngine templateEngine;

    public void sendMail(String to, String subject, String requestContent) {
        MimeMessagePreparator message = mimeMessage -> {
            String content = setThymeleafContent(requestContent);

            log.info("content: {}", content);

            MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
            messageHelper.setTo(to);
            messageHelper.setSubject(subject);
            messageHelper.setText(content, true); //string, boolean (is this html file?)
        };

        javaMailSender.send(message);
    }

    // convert to content using thymeleaf template
    private String setThymeleafContent(String content) {
        Context context = new Context();
        context.setVariable("content", content);
        return templateEngine.process("mail-template", context);
    }
}
