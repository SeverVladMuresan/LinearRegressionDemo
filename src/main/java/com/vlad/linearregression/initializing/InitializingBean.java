package com.vlad.linearregression.initializing;

import com.vlad.linearregression.service.token.BitcoinService;
import com.vlad.linearregression.service.token.CoinApiBitcoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class InitializingBean {

    public static final String BAD_COINAPI_API_KEY_ERROR_MESSAGE_FORMAT = "You are using CoinApiBitcoinService, but " +
            "the api key provided should be an UUID. Please make sure the key is set correctly. Provided api key: %s.";

    @Autowired
    private BitcoinService bitcoinService;

    @Value("${coinapi_api_key}")
    private String COINAPI_API_KEY;

    @SuppressWarnings("ResultOfMethodCallIgnored") // The value of the UUID is not of interest
    @EventListener
    public void onApplicationEvent(ContextRefreshedEvent event) {

        if (bitcoinService instanceof CoinApiBitcoinService) {
            try {
                UUID.fromString(COINAPI_API_KEY);
            } catch (IllegalArgumentException exception) {
                String errMsg = String.format(BAD_COINAPI_API_KEY_ERROR_MESSAGE_FORMAT, COINAPI_API_KEY);
                throw new RuntimeException(errMsg);
            }
        }

    }

}