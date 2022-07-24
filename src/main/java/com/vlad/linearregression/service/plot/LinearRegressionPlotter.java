package com.vlad.linearregression.service.plot;

import com.vlad.linearregression.model.CoinPriceData;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class LinearRegressionPlotter {

    public CoinPriceData plot(CoinPriceData coinPriceDataStartEntry, double slope, LocalDateTime predictionTimestamp) {
        LocalDateTime startTimeStamp = coinPriceDataStartEntry.timestamp();
        long diffInDays = DAYS.between(startTimeStamp, predictionTimestamp);

        double x0 = 0;
        double y0 = coinPriceDataStartEntry.priceInUsd().doubleValue();
        double y1 = plot(slope, x0, y0, diffInDays);
        BigDecimal predictionPriceInUsd = BigDecimal.valueOf(y1);

        return new CoinPriceData(coinPriceDataStartEntry.coinType(), predictionTimestamp, predictionPriceInUsd);
    }

    private double plot(double slope, double x0, double y0, double x1) {
        return slope * (x1 - x0) + y0;
    }

}
