package com.vlad.linearregression.chart;

import com.vlad.linearregression.model.CoinPriceData;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.util.List;

public class LinearRegressionTimeseriesChart extends JFrame {

    private final static String CHART_TITLE = "Liner Regression Timeseries Chart";
    private final static String KNOWN_TIMESERIES_TITLE = "Known";
    private final static String PREDICTION_TITLE = "Prediction";

    public LinearRegressionTimeseriesChart(List<CoinPriceData> knownEntries,
                                           List<CoinPriceData> predictionEntries) {
        super(CHART_TITLE);
        XYDataset dataset = createDataset(knownEntries, predictionEntries);
        JFreeChart chart = ChartFactory.createTimeSeriesChart(
                "Time Series Chart Example", // Chart
                "Date", // X-Axis Label
                "Price", // Y-Axis Label
                dataset);

        XYPlot plot = (XYPlot) chart.getPlot();
        plot.setBackgroundPaint(new Color(255, 228, 196));

        ChartPanel panel = new ChartPanel(chart);
        setContentPane(panel);
    }

    private XYDataset createDataset(List<CoinPriceData> knownEntries, List<CoinPriceData> linearRegressionEntries) {
        TimeSeriesCollection dataset = new TimeSeriesCollection();

        TimeSeries knownEntriesTimeSeries = createTimeSeries(KNOWN_TIMESERIES_TITLE, knownEntries);
        TimeSeries linearRegressionTimeSeries = createTimeSeries(PREDICTION_TITLE, linearRegressionEntries);
        dataset.addSeries(knownEntriesTimeSeries);
        dataset.addSeries(linearRegressionTimeSeries);

        return dataset;
    }

    private TimeSeries createTimeSeries(String timeSeriesTitle, List<CoinPriceData> entries) {
        TimeSeries timeSeries = new TimeSeries(timeSeriesTitle);

        for (CoinPriceData entry : entries) {
            LocalDateTime timestamp = entry.timestamp();
            Day date = new Day(timestamp.getDayOfMonth(), timestamp.getMonthValue(), timestamp.getYear());
            timeSeries.add(date, entry.priceInUsd());
        }

        return timeSeries;
    }

}