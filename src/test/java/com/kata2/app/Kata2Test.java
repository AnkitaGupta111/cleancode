package com.kata2.app;

import com.kata2.app.football.FootballFileReader;
import com.kata2.app.weather.WeatherFileReader;
import org.junit.Assert;
import org.junit.Test;

public class Kata2Test {
    @Test
    public void testWeatherData() throws Exception {
        Assert.assertEquals(14, new WeatherFileReader()
                .getSmallestTempSpread("src/main/assets/weather.dat", new String[]{"Dy", "mo"}));
    }

    @Test
    public void testFootballData() throws Exception {
        Assert.assertEquals("Aston_Villa", new FootballFileReader()
                .getSmallestGoalDifference("src/main/assets/football.dat", new String[]{"Team", "-"}));
    }
}
