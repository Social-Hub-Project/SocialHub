package com.application.socialhub.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    @Bean
    public JavaMailSenderImpl javaMailSender() {


        return new JavaMailSenderImpl();
    }
}
