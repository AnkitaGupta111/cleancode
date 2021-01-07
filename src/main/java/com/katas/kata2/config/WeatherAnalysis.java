package com.katas.kata2.config;

import com.katas.kata2.product.FileHandler;
import com.katas.kata2.product.weather.Day;
import com.katas.kata2.product.weather.WeatherFileHandler;

import java.util.Comparator;

public class WeatherAnalysis {
    private final FileHandler<Day> iFileHandler;

    public WeatherAnalysis(String file) {
        this(new WeatherFileHandler(file));
    }

    private WeatherAnalysis(FileHandler fileHandler) {
        iFileHandler = fileHandler;
    }

    /**
     * method to get least temperature difference of a day
     * @return least difference
     */
    public int getSmallestTemperatureSpread() {
        return iFileHandler.getFileData()
                .stream()
                .min(Comparator.comparingDouble(Day::getTempDifference))
                .map(Day::getDayNumber)
                .orElseThrow();
    }
}
