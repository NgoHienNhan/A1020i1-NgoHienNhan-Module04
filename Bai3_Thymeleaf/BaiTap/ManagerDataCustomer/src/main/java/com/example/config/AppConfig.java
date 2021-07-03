package com.example.config;


import com.example.model.service.CustomerService;
import com.example.model.service.CustomerServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public CustomerService customerService() {
        return new CustomerServiceImpl();
    }
}
