package com.katas.kata2.weatherData;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WeatherData {
    private final int day;
    private final double maximumTemperature;
    private final double minimumTemperature;

    /**
     * returns the temperature difference between the maximum and minimum temperature
     *
     * @return temperature-difference
     */
    public double getTemperatureDifference() {
        return maximumTemperature - minimumTemperature;
    }
}