package com.katas.kata2.services.impl;

import com.katas.kata2.pojos.WeatherForDay;
import com.katas.kata2.services.FileReader;

import java.util.Comparator;

public class WeatherReporter {
    private final FileReader<WeatherForDay> dayFileReader;

    public WeatherReporter(String filePath) {
        this(new WeatherFileReader<>(filePath));
    }

    private WeatherReporter(FileReader<WeatherForDay> fileReader) {
        dayFileReader = fileReader;
    }

    /**
     * @return least temperature difference in a day
     */
    public int getLeastTemperatureDiff() {
        return dayFileReader.getFileData()
                .stream()
                .min(Comparator.comparingDouble(WeatherForDay::getTemperatureDifference))
                .map(WeatherForDay::getDayNumber)
                .orElseThrow();
    }
}
