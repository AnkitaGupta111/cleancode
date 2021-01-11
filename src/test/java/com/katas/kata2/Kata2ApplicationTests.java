package com.katas.kata2;

import com.katas.kata2.football.FootballFileReader;
import com.katas.kata2.weatherData.WeatherFileReader;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class Kata2ApplicationTests {

    @Test
    public void testFootballData() {
        assertEquals("Aston_Villa", new FootballFileReader().getMinimumGoalDifference("src/main/resources/football.dat", new String[]{"Team", "-"}));
    }

    @Test
    public void testWeatherData() {
        assertEquals(14, new WeatherFileReader().getMinimumTemperatureSpread("src/main/resources/weather.dat", new String[]{"Dy", "mo"}));
    }
}
