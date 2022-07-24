package com.vlad.linearregression.service.token;

import com.vlad.linearregression.model.CoinPriceData;
import com.vlad.linearregression.model.CoinType;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@Profile("mock")
@Service
public class MockBitcoinService implements BitcoinService {

    @Override
    public List<CoinPriceData> getBitcoinPriceInDateTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {

        List<CoinPriceData> coinPriceDataList = new LinkedList<>();

        for (LocalDateTime date = startDateTime; date.isBefore(endDateTime) || date.isEqual(endDateTime);
             date = date.plusDays(1)) {
            int randomNum = ThreadLocalRandom.current().nextInt(100, 1000);
            CoinPriceData cpd = new CoinPriceData(CoinType.BITCOIN, date, BigDecimal.valueOf(randomNum));
            coinPriceDataList.add(cpd);
        }

        return coinPriceDataList;
    }

}

