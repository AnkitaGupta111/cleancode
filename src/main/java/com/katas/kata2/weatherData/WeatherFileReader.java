package com.katas.kata2.weatherData;

import com.katas.kata2.FileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class WeatherFileReader extends FileHandler {

    /**
     * returns the list containing day, maximumTemperature, minimumTemperature by trimming
     * the respective substrings from the filtered lines
     *
     * @param fileName                  name of the weather.dat file
     * @param skipLineWithStartingWords String array containing the words to skip lines
     * @return WeatherData list containing day, maximumTemperature, minimumTemperature
     */
    private List<WeatherData> getRequiredWeatherData(final String fileName, final String[] skipLineWithStartingWords) {
        List<WeatherData> data = new ArrayList<>();
        getFilteredFileData(fileName, skipLineWithStartingWords).stream().forEach(rowData -> {
            int day = Integer.parseInt(rowData.trim().substring(0, 3).trim());
            float maximumTemperature = Float.parseFloat(rowData.trim().substring(3, 7).trim().replaceAll("[^0-9]", ""));
            float minimumTemperature = Float.parseFloat(rowData.trim().substring(8, 12).trim().replaceAll("[^0-9]", ""));
            data.add(new WeatherData(day, maximumTemperature, minimumTemperature));
        });
        return data;
    }

    /**
     * calculates the minimum temp difference and mapping to the respective day
     *
     * @param fileName                  name of the weather.dat file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return day with minimum temperature spread
     */
    public int getMinimumTemperatureSpread(final String fileName, final String[] skipLineWithStartingWords) {
        return getRequiredWeatherData(fileName, skipLineWithStartingWords)
                .stream()
                .min(Comparator.comparingDouble(WeatherData::getTemperatureDifference))
                .map(WeatherData::getDay)
                .orElseThrow(null);
    }
}
