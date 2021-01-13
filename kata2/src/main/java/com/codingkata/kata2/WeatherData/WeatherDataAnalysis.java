package com.codingkata.kata2.WeatherData;

import com.codingkata.kata2.Entity.WeatherRecord;
import com.codingkata.kata2.FileHandler.FileHandlerInterface;

import java.util.Comparator;

public class WeatherDataAnalysis {
    private final FileHandlerInterface<WeatherRecord> fileHandlerInterface;

    public WeatherDataAnalysis(String file) {
        fileHandlerInterface = new WeatherDataHandler(file);
    }

    /**
     *
     * @return smallest teamperature spread
     */
    public int getSmallestTemperatureDifference(){
        return fileHandlerInterface.getFileData()
                .stream()
                .min(Comparator.comparing(WeatherRecord::getTemperatureDifference))
                .map(WeatherRecord::getDayOfMonth)
                .orElseThrow();
    }
}
