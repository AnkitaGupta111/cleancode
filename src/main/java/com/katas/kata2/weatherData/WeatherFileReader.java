package com.katas.kata2.weatherData;

import com.katas.kata2.FileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeatherFileReader extends FileHandler {

    /**
     * returns the list containing day, maximumTemperature, minimumTemperature by splitting input data lines using regex
     *
     * @param filePath                  filePath for the weather.dat file
     * @param skipLineWithStartingWords String array containing the words to skip lines
     * @return WeatherData list containing day, maximumTemperature, minimumTemperature
     */
    private List<WeatherData> getRequiredWeatherData(final String filePath, final String[] skipLineWithStartingWords) {
        List<WeatherData> data = new ArrayList<>();
        getFilteredFileData(filePath, skipLineWithStartingWords).stream().forEach(rowData -> {
            String[] dataArray = rowData.split("\\s+");
            int day = Integer.parseInt(dataArray[1]);
            float maximumTemperature = Float.parseFloat(dataArray[2].replaceAll("[^0-9]", ""));
            float minimumTemperature = Float.parseFloat(dataArray[3].replaceAll("[^0-9]", ""));
            data.add(new WeatherData(day, maximumTemperature, minimumTemperature));
        });
        return data;
    }

    /**
     * calculates the minimum temp difference and mapping to the respective day
     *
     * @param filePath                  filePath for the weather.dat file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return day with minimum temperature spread
     */
    public int getMinimumTemperatureSpread(final String filePath, final String[] skipLineWithStartingWords) {
        return getRequiredWeatherData(filePath, skipLineWithStartingWords)
                .stream()
                .min(Comparator.comparingDouble(WeatherData::getTemperatureDifference))
                .map(WeatherData::getDay)
                .orElseThrow();
    }
}
