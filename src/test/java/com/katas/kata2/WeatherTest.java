package com.katas.kata2;

import com.katas.kata2.services.impl.WeatherReporter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeatherTest {
    @Test
    void weatherTest() {
        WeatherReporter weatherReporter = new WeatherReporter("weather.dat");
        assertEquals(14, weatherReporter.getLeastTemperatureDiff(), 0);
    }
}
