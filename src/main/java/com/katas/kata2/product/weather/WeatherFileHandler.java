package com.katas.kata2.product.weather;

import com.katas.kata2.product.FileHandler;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherFileHandler<T> implements FileHandler<T> {

    private static final String HEADER = "Dy";
    private static final String TOTALS = "mo";
    private final String fileName;


    public WeatherFileHandler(String file) {
        fileName = file;
    }

    @Override
    public List getFileData() {
        List days;
        days = readFile(fileName)
                .stream()
                .filter(line -> !skipLine(line))
                .map(line -> getDayFromFileLine(line))
                .collect(Collectors.toList());
        return days;
    }

    @Override
    public boolean skipLine(String line) {
        return line.trim().startsWith(HEADER) || line.trim().startsWith(TOTALS) || line.isBlank();
    }

    /**
     * method to get data of day from line
     * @param line
     * @return Day
     */
    private Day getDayFromFileLine(String line) {
        int dayNumber = Integer.parseInt(line.substring(0, 4).trim().replaceAll("[^0-9]", ""));
        Double maxTemp = Double.parseDouble(line.substring(4, 8).trim().replaceAll("[^0-9.]", ""));
        Double minTemp = Double.parseDouble(line.substring(8, 14).trim().replaceAll("[^0-9.]", ""));
        return new Day(dayNumber, maxTemp, minTemp);
    }
}

