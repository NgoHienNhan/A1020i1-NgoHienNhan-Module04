package com.example.config;

import com.example.service.EmailService;
import com.example.service.EmailServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public EmailService calculatorService() {
        return new EmailServiceImpl();
    }
}
