package com.katas.kata2;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WeatherDataProcessor {
    String fileName;

    List<String[]> dataRows = new ArrayList<>();

    WeatherDataProcessor() throws IOException {
        fileName = "weather.dat";
        try {
            readDataFile();
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
            throw new IOException(ioException.getMessage());
        }
    }

    void readDataFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String fileLine;

        while ((fileLine = br.readLine()) != null) {
            if (!fileLine.trim().equals("")) {
                String line = fileLine.trim();
                String[] colData = line.split("\\s+");
                dataRows.add(colData);
            }
        }
        br.close();
    }

    int getSmallestTempSpreadDayNumber() {
        dataRows.remove(0);
        dataRows.remove(dataRows.size() - 1);
        int dayNumberWithSmallestTempDiff = 0;
        int smallestTempSpread = Integer.MAX_VALUE;
        int minTempIndex = 2;
        int maxTempIndex = 1;
        int dayNUmberIndex = 0;

        for (String[] row : dataRows) {
            String maxTempData = row[maxTempIndex];
            String minTempData = row[minTempIndex];

            int maxTemp = Integer.parseInt(maxTempData.replaceAll("\\D", ""));
            int minTemp = Integer.parseInt(minTempData.replaceAll("\\D", ""));
            int tempDif = maxTemp - minTemp;
            if (tempDif < smallestTempSpread) {
                smallestTempSpread = tempDif;
                dayNumberWithSmallestTempDiff = Integer.parseInt(row[dayNUmberIndex]);
            }
        }
        return dayNumberWithSmallestTempDiff;
    }

}
