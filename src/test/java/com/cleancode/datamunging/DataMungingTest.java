package com.cleancode.datamunging;

import org.junit.Assert;
import org.junit.Test;

public class DataMungingTest {
    @Test
    public void testGetWeatherSpread() {
        Assert.assertEquals(2, Application.getWeatherSpread());
    }

    @Test
    public void testGetLeagueTeamNameWthMinGoalsDiff() {
        Assert.assertEquals("Aston_Villa", Application.getLeagueTeamWithMinDiff());
    }
}
