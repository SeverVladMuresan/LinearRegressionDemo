package com.vlad.linearregression;

import com.vlad.linearregression.service.prediction.PredictionService;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import javax.swing.*;
import java.time.LocalDateTime;

@SpringBootApplication
public class LinearRegressionDemoApplication {

    public static void main(String[] args) {
        SpringApplicationBuilder builder = new SpringApplicationBuilder(LinearRegressionDemoApplication.class);

        builder.headless(false);
        ConfigurableApplicationContext context = builder.run(args);

        SwingUtilities.invokeLater(() -> {
            PredictionService predictionService = context.getBean(PredictionService.class);
            predictionService.predictBitcoinUsd(LocalDateTime.now().plusDays(7));
        });

    }

}
