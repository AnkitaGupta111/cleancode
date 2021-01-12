package com.katas.kata2;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class WeatherDataProcessor {
    private String fileName;
    private List<String[]> dataRows = new ArrayList<>();

    WeatherDataProcessor(String fileName) {
        this.fileName = fileName;
    }

    private void readDataFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String fileLine;

        while ((fileLine = br.readLine()) != null) {
            String line = fileLine.trim();
            if (!line.equals("") && !line.startsWith("mo") && !line.startsWith("Dy")) {
                String[] colData = line.split("\\s+");
                dataRows.add(colData);
            }
        }
        br.close();
    }

    /**
     * find day number having minimum Temp spread
     *
     * @return day number having minimum Temp spread
     * @throws IOException when file provided to read not found
     */
    int getSmallestTempSpreadDayNumber() throws IOException {
        readDataFile();
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
