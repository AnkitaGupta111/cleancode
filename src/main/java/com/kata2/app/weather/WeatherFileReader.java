package com.kata2.app.weather;

import com.kata2.app.FileHandler;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeatherFileReader extends FileHandler {

    private List<WeatherData> getRequiredWeatherData(final String fileName, final String[] skipLineWithStartingWords) {
        List<WeatherData> data = new ArrayList<>();
        for(String rowData : getFileData(fileName, skipLineWithStartingWords)) {
            int day = Integer.parseInt(rowData.split(" +")[1]);
            float maxTemp = Float.parseFloat(rowData.split(" +")[2].replaceAll("[^0-9]", ""));
            float minTemp = Float.parseFloat(rowData.split(" +")[3].replaceAll("[^0-9]", ""));
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
