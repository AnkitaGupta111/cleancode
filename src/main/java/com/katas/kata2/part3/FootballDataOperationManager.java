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
     * gives team name having min diff in the total number of goals scored for(F col) and against each team in that season(A col)
     *
     * @return team name having min diff in total number of goals scored for(F col) and against each team(A col)
     */
    public String getMinDiffInFandATeam() {
        List<Integer> rowNumberToSkip = Arrays.asList(1);
        List<Integer> colNumberToInclude = Arrays.asList(1, 6, 7);
        List<String[]> requiredDataSet = fileDataHandler.getRequiredDataSet(rowNumberToSkip, colNumberToInclude);
        return requiredDataSet.stream().min(DataOperationHelper::getComparatorForMinDiff).get()[0];
    }

}
