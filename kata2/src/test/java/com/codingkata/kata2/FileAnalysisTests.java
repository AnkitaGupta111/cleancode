package com.codingkata.kata2;

import com.codingkata.kata2.SoccerLeague.SoccerLeagueDataAnalysis;
import com.codingkata.kata2.WeatherData.WeatherDataAnalysis;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class FileAnalysisTests {

    private SoccerLeagueDataAnalysis soccerLeagueDataAnalysis=new SoccerLeagueDataAnalysis("src/main/resources/football.dat");
    private WeatherDataAnalysis weatherDataAnalysis=new WeatherDataAnalysis("src/main/resources/weather.dat");
    @Test
    void testWeatherData(){
        Assertions.assertEquals(14,weatherDataAnalysis.getSmallestTemperatureDifference());
    }
    @Test
    void testSoccerLeagueData(){
        Assertions.assertEquals("Aston_Villa",soccerLeagueDataAnalysis.getSmallestGoalDifference());
    }
}
