package com.example.config;


import com.example.model.service.ProductService;
import com.example.model.service.ProductServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public ProductService productService() {
        return new ProductServiceImpl();
    }
}
