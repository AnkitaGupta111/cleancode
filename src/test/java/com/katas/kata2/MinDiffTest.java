package com.katas.kata2;

import com.katas.kata2.part3.FootballDataOperationManager;
import com.katas.kata2.part3.WeatherDataOperationManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;


public class MinDiffTest {

    @Test
    void testSmallestTempSpreadDayNumber() throws IOException {
        WeatherDataOperationManager wm = new WeatherDataOperationManager("weather.dat");
        Assertions.assertEquals("14", wm.getSmallestTempSpreadDayNumber());

    }

    @Test
    void testgetMinDiffInFandATeam() throws IOException {
        FootballDataOperationManager fm = new FootballDataOperationManager("football.dat");
        Assertions.assertEquals("Aston_Villa", fm.getMinDiffInFandATeam());

    }
}
