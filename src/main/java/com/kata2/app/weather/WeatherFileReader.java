package com.kata2.app.weather;

import com.kata2.app.FileHandler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeatherFileReader extends FileHandler {

    private List<WeatherData> getRequiredWeatherData(final String fileName, final String[] skipLineWithStartingWords) {
        List<WeatherData> data = new ArrayList<>();
        for(String rowData : getFileData(fileName, skipLineWithStartingWords)) {
            int day = Integer.parseInt(rowData.trim().substring(0,3).trim());
            float maxTemp = Float.parseFloat(rowData.trim().substring(3,7).trim().replaceAll("[^0-9]", ""));
            float minTemp = Float.parseFloat(rowData.trim().substring(8,12).trim().replaceAll("[^0-9]", ""));
            data.add(new WeatherData(day, maxTemp, minTemp ));
        }
        return data;
    }

    public int getSmallestTempSpread(final String fileName, final String[] skipLineWithStartingWords) {
        return getRequiredWeatherData(fileName, skipLineWithStartingWords)
                .stream()
                .min(Comparator.comparingDouble(WeatherData::getTempDifference))
                .map(WeatherData::getDay)
                .orElseThrow(null);
    }


}
