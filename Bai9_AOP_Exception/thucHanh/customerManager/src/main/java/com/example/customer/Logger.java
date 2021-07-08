package com.example.customer;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@Aspect
public class Logger {
    @Pointcut(value = "execution(* com.example.customer.controller.ControllerCustomer.*(..))")
    private void logDisplayingEmployee() {
    }

    @AfterThrowing(pointcut = "execution(* com.example.customer.controller.ControllerCustomer.showAll(..))")
    public void log(JoinPoint joinPoint) {
        System.err.println("start method name: " + joinPoint.getSignature().getName() + "Time: " + LocalDate.now());
    }
}
