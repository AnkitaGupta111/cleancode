package com.codingkata.kata2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class WeatherRecord {
    private int dayOfMonth;
    private double maximumDailyTemperature;
    private double minimumDailyTemperature;

    /**
     *
     * @return temperature difference
     */
    public double getTemperatureDifference() {
        return (maximumDailyTemperature-minimumDailyTemperature);
    }
}
