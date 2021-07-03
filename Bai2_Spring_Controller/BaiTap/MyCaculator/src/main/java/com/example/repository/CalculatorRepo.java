package com.example.repository;

import org.springframework.stereotype.Service;

@Service
public interface CalculatorRepo {
    double calculator(Integer number1, Integer number2, String operator);
}
