package com.vlad.linearregression.validator.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;


@Aspect
@Component
public class LinearRegressionCalculatorValidator {

    public static final String ERROR_MESSAGE = "Must have at Least 2 entries to calculate linear regression.";

    @Around("target(com.vlad.linearregression.service.linearregression.LinearRegressionCalculator) && " +
            "execution(* *..calculateLinearRegressionSlope*(java.util.List<java.math.BigDecimal>))")
    public Object calculateLinearRegressionSlopeValidator(ProceedingJoinPoint joinPoint) throws Throwable {

        Object[] args = joinPoint.getArgs();

        @SuppressWarnings("unchecked") // "Unchecked cast necessary when getting method arguments using AOP"
        List<BigDecimal> coinPrices = (List<BigDecimal>) args[0];

        if (coinPrices.size() < 2) {
            throw new RuntimeException(ERROR_MESSAGE);
        }

        return joinPoint.proceed();
    }

}