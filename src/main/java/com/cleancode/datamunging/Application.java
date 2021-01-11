package com.cleancode.datamunging;

import com.cleancode.datamunging.interfaces.ISourceData;
import com.cleancode.datamunging.models.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Application {
    static TreeMap<Integer, ISourceData> weatherMap = new TreeMap<>();
    static TreeMap<Integer, ISourceData> leagueMap = new TreeMap<>();

    static List<ISourceData> weatherList = new ArrayList<>();
    static List<ISourceData> leagueList = new ArrayList<>();

    static DataService dataService = new DataService();
    static ClassLoader classLoader = Application.class.getClassLoader();


    public static int getWeatherSpread() {
        File weatherFile = new File(classLoader.getResource("weather.dat").getFile());
        dataService.importData(weatherFile,
                weatherList,
                new WeatherDataFactory()
        );

        for(ISourceData iSourceData: weatherList) {
            weatherMap.put(findSpread((ImmutableWeatherData) iSourceData), iSourceData);
        }
        return weatherMap.firstEntry().getKey();
    }

    public static String getLeagueTeamWithMinDiff() {
        File leagueFile = new File(classLoader.getResource("football.dat").getFile());
        dataService.importData(leagueFile,
                leagueList,
                new LeagueDataFactory()
        );

        for(ISourceData iSourceData1: leagueList) {
            leagueMap.put(findGoalsDiff((ImmutableLeagueData) iSourceData1), iSourceData1);
        }
        return ((ImmutableLeagueData)leagueMap.firstEntry().getValue()).getTeamName();
    }

    static int findSpread(ImmutableWeatherData data) {
        return data.getMaxTemp() - data.getMinTemp();
    }
    static int findGoalsDiff(ImmutableLeagueData data) {return Math.abs(data.getForGoals() - data.getAgainstGoals());}
}
