package com.example.config;

import com.example.service.CalculatorService;
import com.example.service.CalculatorServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CalculatorService calculatorService() {
        return new CalculatorServiceImpl();
    }
}
