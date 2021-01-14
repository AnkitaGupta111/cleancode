package com.codingkata.kata2.WeatherData;

import com.codingkata.kata2.Entity.WeatherRecord;
import com.codingkata.kata2.FileHandler.FileHandlerInterface;
import java.util.List;
import java.util.stream.Collectors;

public class WeatherDataHandler implements FileHandlerInterface<WeatherRecord> {
    private final String filePath;

    public WeatherDataHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     *
     * @return list of Weather objects
     */
    @Override
    public List<WeatherRecord> getFileData() {
        List<WeatherRecord> weatherObjects = readFile(filePath)
                .stream()
                .filter(line -> (!line.isBlank() &&(!line.contains("Dy")) &&!line.contains("mo")))
                .map(line -> getWeatherObjectFromFileLine(line))
                .collect(Collectors.toList());
        return weatherObjects;
    }

    /**
     *
     * @param line represents line in file
     * @return weather record from each line
     */
    private WeatherRecord getWeatherObjectFromFileLine(String line) {
        String [] dataInLines=line.replaceAll("[]*]", "").split("\\s+");
        int day=Integer.parseInt(dataInLines[1]);
        double maxTemperature = Double.parseDouble(dataInLines[2]);
        double minTemperature = Double.parseDouble(dataInLines[3]);
        return new WeatherRecord(day,maxTemperature,minTemperature);
    }

}
