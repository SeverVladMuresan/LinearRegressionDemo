package com.vlad.linearregression.service.linearregression;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SimpleLinearRegressionCalculator implements LinearRegressionCalculator {

    public double calculateLinearRegressionSlope(List<BigDecimal> coinPrices) {

        SimpleRegression simpleRegression = new SimpleRegression();
        for (int i = 0; i < coinPrices.size(); i++) {
            simpleRegression.addData(i, coinPrices.get(i).doubleValue());
        }
        return simpleRegression.getSlope();
    }

}
