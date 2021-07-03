package com.example.config;


import com.example.dao.UserDao;
import com.example.dao.UserDaoimpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public UserDao userDao() {
        return new UserDaoimpl();
    }
}
