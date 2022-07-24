package com.vlad.linearregression.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.vlad.linearregression.util.IsoDateToLocalDateTimeDeserializer;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@JsonIgnoreProperties(ignoreUnknown = true)
public record CoinApiTimeseriesDTO(LocalDateTime timeOpen, LocalDateTime timeClose, BigDecimal rateOpen,
                                   BigDecimal rateClose) {

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CoinApiTimeseriesDTO(
            @JsonProperty("time_open")
            @JsonDeserialize(using = IsoDateToLocalDateTimeDeserializer.class)
            LocalDateTime timeOpen,
            @JsonProperty("time_close")
            @JsonDeserialize(using = IsoDateToLocalDateTimeDeserializer.class)
            LocalDateTime timeClose,
            @JsonProperty("rate_open")
            BigDecimal rateOpen,
            @JsonProperty("rate_close")
            BigDecimal rateClose) {
        this.timeOpen = timeOpen;
        this.timeClose = timeClose;
        this.rateOpen = rateOpen;
        this.rateClose = rateClose;
    }

}
