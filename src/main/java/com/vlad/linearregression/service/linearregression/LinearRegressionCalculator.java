package com.vlad.linearregression.service.linearregression;

import java.math.BigDecimal;
import java.util.List;

public interface LinearRegressionCalculator {

    double calculateLinearRegressionSlope(List<BigDecimal> coinPrices);

}
