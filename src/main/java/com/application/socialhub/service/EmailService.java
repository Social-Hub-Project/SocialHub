package com.application.socialhub.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Properties;

@Service
public class EmailService implements EmailSender{

    private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);

    private final JavaMailSenderImpl mailSender;

    public EmailService(JavaMailSenderImpl mailSender) {

        this.mailSender = mailSender;
        this.mailSender.setHost("smtp.wp.pl");
        this.mailSender.setPort(587);

        /*
        *            DON'T COMMIT THIS CHANGES!!!!!!!!!!!!!
        *
        * Change password and username to your own valid email and password,
        * I don't know why this doesn't work with newly created emails...
        * I guess it is related to giving access to less secure applications on email server side.
        *
        * Don't forget to change lines "smtp.wp.pl"  and "helper.setFrom("socialhubapplication@wp.pl");"
        * */
        this.mailSender.setPassword("password");
        this.mailSender.setUsername("socialhubapplication@wp.pl");

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "true");
    }

    @Async
    @Override
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");

            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom("socialhubapplication@wp.pl");

            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            LOGGER.error("failed to send email", e);
            throw new IllegalStateException("failed to send email");
        }
    }
}
