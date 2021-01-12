package com.katas.kata2.services.impl;

import com.katas.kata2.pojos.WeatherForDay;
import com.katas.kata2.services.FileReader;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class WeatherFileReader<T> implements FileReader<T> {

    private static final String REGEX = "[^0-9]";
    private static final String HEADING = "Dy";
    private static final String AGGREGATE = "mo";
    private final String filePath;

    @Override
    public boolean skipLine(String line) {
        return line.trim().startsWith(HEADING) || line.trim().startsWith(AGGREGATE) || line.isBlank();
    }

    @Override
    public List getFileData() {
        return readFile(filePath)
                .stream()
                .filter(line -> !skipLine(line))
                .map(this::getDayFromFileLine)
                .collect(Collectors.toList());
    }

    /**
     * Converts a row of .dat file to WeatherForDay pojo
     *
     * @param line Line item / Row of .dat file
     * @return WeatherForDay converted from line item string
     */
    private WeatherForDay getDayFromFileLine(String line) {
        WeatherForDay weatherForDay = new WeatherForDay();
        weatherForDay.setDayNumber(Integer.parseInt(line.substring(0, 4).trim().replaceAll(REGEX, "")));
        weatherForDay.setMaxTemp(Double.parseDouble(line.substring(4, 8).trim().replaceAll(REGEX, "")));
        weatherForDay.setMinTemp(Double.parseDouble(line.substring(8, 14).trim().replaceAll(REGEX, "")));
        return weatherForDay;
    }
}
