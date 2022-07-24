package com.vlad.linearregression.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record CoinPriceData(CoinType coinType, LocalDateTime timestamp, BigDecimal priceInUsd) {

}
