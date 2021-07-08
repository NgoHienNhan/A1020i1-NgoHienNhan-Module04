package com.example.demo;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
public class log {
    @Pointcut("execution(* com.example.demo.BookController.*(..))")
    private void allMethodPointCut() {
    }

    @Before("allMethodPointCut()")
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println("Before method: "+joinPoint.getSignature().getName()+ " Time: "+ LocalDate.now());
    }

    @AfterReturning("allMethodPointCut()")
    public void afterReturningMethod(JoinPoint joinPoint){
        System.out.println("After returning method: "+joinPoint.getSignature().getName()+ " Time: "+ LocalDate.now());
    }
}
