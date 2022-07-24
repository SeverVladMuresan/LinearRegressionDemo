package com.vlad.linearregression.service.prediction;

import com.vlad.linearregression.model.CoinPriceData;
import com.vlad.linearregression.service.chart.ChartService;
import com.vlad.linearregression.service.linearregression.LinearRegressionCalculator;
import com.vlad.linearregression.service.plot.LinearRegressionPlotter;
import com.vlad.linearregression.service.token.BitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PredictionService {

    private final static Duration PAST_DATE_LOOKUP_TIME_AMOUNT = Duration.ofDays(7);
    @Autowired
    BitcoinService bitcoinService;
    @Autowired
    LinearRegressionCalculator linearRegressionCalculator;
    @Autowired
    LinearRegressionPlotter linearRegressionPlotter;
    @Autowired
    ChartService chartService;

    public void predictBitcoinUsd(LocalDateTime predictionDate) {

        validatePredictionDate(predictionDate);

        List<CoinPriceData> pastBitCoinPrices = getPastCoinPriceData();
        double linearRegressionSlope = calculateLinearRegressionSlope(pastBitCoinPrices);
        CoinPriceData predictedCoinPriceData = plotPredictedValueAtDate(linearRegressionSlope, pastBitCoinPrices,
                predictionDate
        );

        List<CoinPriceData> linearRegressionEntries = getLinearRegressionEntries(pastBitCoinPrices,
                predictedCoinPriceData);

        chartService.generateLinearRegressionTimeseriesChart(pastBitCoinPrices, linearRegressionEntries);

    }

    private void validatePredictionDate(LocalDateTime predictionDate) {
        LocalDateTime now = LocalDateTime.now();
        if (predictionDate.isBefore(now)) {
            throw new RuntimeException("predictionDate must be after current date");
        }
    }

    private List<CoinPriceData> getLinearRegressionEntries(List<CoinPriceData> pastBitCoinPrices,
                                                           CoinPriceData predictedCoinPriceData) {
        return List.of(pastBitCoinPrices.get(0), predictedCoinPriceData);
    }


    private List<CoinPriceData> getPastCoinPriceData() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime pastDataStart = now.minus(PAST_DATE_LOOKUP_TIME_AMOUNT);
        return bitcoinService.getBitcoinPriceInDateTimeRange(pastDataStart, now);
    }

    private double calculateLinearRegressionSlope(List<CoinPriceData> pastBitCoinPrices) {
        List<BigDecimal> bitCoinDailyPrices = pastBitCoinPrices.stream().map(CoinPriceData::priceInUsd).toList();
        return linearRegressionCalculator.calculateLinearRegressionSlope(bitCoinDailyPrices);
    }

    private CoinPriceData plotPredictedValueAtDate(double linearRegressionSlope,
                                                   List<CoinPriceData> pastBitCoinPrices,
                                                   LocalDateTime predictionDate) {
        return linearRegressionPlotter.plot(pastBitCoinPrices.get(0), linearRegressionSlope, predictionDate);
    }

}
