package com.katas.kata2.part3;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * for operation on football data file
 */
public class FootballDataOperationManager {
    FileDataHandler fileDataHandler;

    public FootballDataOperationManager(String fileName) throws IOException {
        fileDataHandler = new FileDataHandler(fileName);
    }

    /**
     * gives team name having min diff in F and A Goals
     *
     * @return team name having min diff in F and A Goals
     */
    public String getMinDiffInFandATeam() {
        List<Integer> rowNumberToSkip = Arrays.asList(1);
        List<Integer> colNumberToInclude = Arrays.asList(1, 6, 7);
        List<String[]> requiredDataSet = fileDataHandler.getRequiredDataSet(rowNumberToSkip, colNumberToInclude);
        return requiredDataSet.stream().min(Helper::getComparatorForMinDiff).get()[0];
    }

}
