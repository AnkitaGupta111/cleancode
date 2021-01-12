package com.katas.kata2.part3;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * for operation on weather data file
 */
public class WeatherDataOperationManager {
    FileDataHandler fileDataHandler;

    public WeatherDataOperationManager(String fileName) throws IOException {
        fileDataHandler = new FileDataHandler(fileName);
    }

    /**
     * gives day number having min Temp spread
     *
     * @return day number having min Temp spread
     */
    public String getSmallestTempSpreadDayNumber() {
        int noOfRows = fileDataHandler.getDataRows().size();
        List<Integer> rowNumberToSkip = Arrays.asList(1, noOfRows);
        List<Integer> colNumberToInclude = Arrays.asList(0, 1, 2);
        List<String[]> requiredDataSet = fileDataHandler.getRequiredDataSet(rowNumberToSkip, colNumberToInclude);

        return requiredDataSet.stream().min(DataOperationHelper::getComparatorForMinDiff).get()[0];

    }


}
