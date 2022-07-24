package com.vlad.linearregression.service.token;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vlad.linearregression.dto.CoinApiTimeseriesDTO;
import com.vlad.linearregression.model.CoinPriceData;
import com.vlad.linearregression.model.CoinType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The name "CoinApiBitcoinService" comes from the name of the used api - https://www.coinapi.io/
 */
@SuppressWarnings("javadoc")
@Profile("!mock")
@Service
public class CoinApiBitcoinService implements BitcoinService {

    private final static CoinType COIN_TYPE = CoinType.BITCOIN;
    private static final String URL_TEMPLATE = "https://rest.coinapi.io/v1/exchangerate/BTC/USD/history?period_id" +
            "=1DAY&time_start=%s&time_end=%s";

    @Value("${coinapi_api_key}")
    private String API_KEY;

    @Override
    public List<CoinPriceData> getBitcoinPriceInDateTimeRange(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        HttpRequest request = getHttpRequest(startDateTime, endDateTime);
        String response = getJsonResponse(request);

        return mapJsonResponse(response);
    }

    private HttpRequest getHttpRequest(LocalDateTime startDateTime, LocalDateTime endDateTime) {
        String url = String.format(URL_TEMPLATE, startDateTime.toLocalDate(), endDateTime.toLocalDate());
        System.out.println(url);
        return HttpRequest.newBuilder().uri(URI.create(url)).header("X-CoinAPI-Key", API_KEY).GET().build();
    }

    private String getJsonResponse(HttpRequest request) {
        try {
            HttpResponse<String> response = HttpClient.newBuilder().build()
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private List<CoinPriceData> mapJsonResponse(String response) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(response, new TypeReference<List<CoinApiTimeseriesDTO>>() {})
                    .stream()
                    .map(this::coinApiTimeseriesDTOtoCoinPriceData)
                    .collect(Collectors.toList());
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private CoinPriceData coinApiTimeseriesDTOtoCoinPriceData(CoinApiTimeseriesDTO coinApiTimeseriesDTO) {
        return new CoinPriceData(COIN_TYPE, coinApiTimeseriesDTO.timeClose(), coinApiTimeseriesDTO.rateClose());
    }

}
