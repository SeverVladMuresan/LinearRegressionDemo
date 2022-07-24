package com.vlad.linearregression.service.chart;

import com.vlad.linearregression.chart.LinearRegressionTimeseriesChart;
import com.vlad.linearregression.model.CoinPriceData;
import org.springframework.stereotype.Service;

import javax.swing.*;
import java.util.List;

@Service
public class ChartService {
    public void generateLinearRegressionTimeseriesChart(List<CoinPriceData> knownEntries,
                                                        List<CoinPriceData> linearRegressionEntries) {
        LinearRegressionTimeseriesChart chart = new LinearRegressionTimeseriesChart(knownEntries,
                linearRegressionEntries);
        chart.setSize(800, 400);
        chart.setLocationRelativeTo(null);
        chart.setVisible(true);
        chart.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

}
