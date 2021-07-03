package com.example.service;

import com.example.repository.CalculatorRepo;
import com.example.repository.CalculatorRepoImpl;

public class CalculatorServiceImpl implements CalculatorService {
    CalculatorRepo calculatorRepo=new CalculatorRepoImpl();
    @Override
    public double calculator(Integer number1, Integer number2, String operator) {
       return calculatorRepo.calculator(number1,number2,operator);
    }
}
