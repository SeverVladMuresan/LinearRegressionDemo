package com.vlad.linearregression.service.linearregression;

import com.vlad.linearregression.validator.aspect.LinearRegressionCalculatorValidator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;


@SpringBootTest
@ActiveProfiles("mock")
class LinearRegressionCalculatorTest {

    @Autowired
    LinearRegressionCalculator linearRegressionCalculator;

    @Test
    void testCalculateLinearRegressionSlope_expectSuccess() {
        List<BigDecimal> testValues = List.of(BigDecimal.ONE, BigDecimal.TEN);
        double slope = linearRegressionCalculator.calculateLinearRegressionSlope(testValues);
        assertEquals(9, slope, 0.01);
    }

    @Test
    void testCalculateLinearRegressionSlope_expectError() {
        List<BigDecimal> testValues = List.of(BigDecimal.ONE);

        RuntimeException runtimeException = assertThrows(RuntimeException.class,
                () -> linearRegressionCalculator.calculateLinearRegressionSlope(testValues));

        assertEquals(runtimeException.getMessage(), LinearRegressionCalculatorValidator.ERROR_MESSAGE);
    }

}