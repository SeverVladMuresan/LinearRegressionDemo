package com.vlad.linearregression.service.token;

import com.vlad.linearregression.model.CoinPriceData;

import java.time.LocalDateTime;
import java.util.List;

public interface BitcoinService {

    List<CoinPriceData> getBitcoinPriceInDateTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime);

}
