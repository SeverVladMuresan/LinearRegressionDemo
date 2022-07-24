package com.vlad.linearregression.service.token;

import com.vlad.linearregression.model.CoinPriceData;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * The class is annotated with @Disabled because only subclasses are meant to be run
 */
@Disabled
public class BitcoinServiceTest {

    @Autowired
    BitcoinService bitcoinService;

    @Test
    void testGetBitcoinPriceInDateTimeRange() {
        LocalDateTime start = LocalDateTime.of(2022, 7, 1, 14, 30);
        LocalDateTime end = LocalDateTime.of(2022, 7, 12, 14, 30);

        List<CoinPriceData> bitcoinPriceInDateTimeRange = bitcoinService
                .getBitcoinPriceInDateTimeRange(start, end);

        assertEquals(12, bitcoinPriceInDateTimeRange.size());
    }
}
