package com.katas.kata2;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FootballMatchDataProcessor {
    private String fileName;
    private List<String[]> dataRows = new ArrayList<>();

    FootballMatchDataProcessor(String fileName) {
        this.fileName = fileName;
    }


    private void readDataFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String fileLine;

        while ((fileLine = br.readLine()) != null) {
            String line = fileLine.trim();
            if (!line.trim().equals("") && !line.contains("--------") && !line.startsWith("Team")) {
                String[] colData = line.split("\\s+-*\\s*");
                dataRows.add(colData);
            }
        }

        br.close();
    }

    /**
     * find team name having min diff in the total number of goals scored for(F col) and against each team in that season(A col)
     *
     * @return team name having min diff in the total number of goals scored for(F col) and against each team in that season(A col)
     * @throws IOException when file provided to read not found
     */
    String getMinDiffInFandATeam() throws IOException {
        readDataFile();
        String teamNameWithMinFAndADiff = null;
        int smallestDiff = Integer.MAX_VALUE;
        int colFIndex = 6;
        int colAIndex = 7;
        int teamNameIndex = 1;

        for (String[] row : dataRows) {
            String forGoalData = row[colFIndex];
            String againstGoalData = row[colAIndex];

            int numberOfForGoal = Integer.parseInt(forGoalData);
            int numberOfAgainstGoal = Integer.parseInt(againstGoalData);
            int goalDiff = Math.abs(numberOfForGoal - numberOfAgainstGoal);
            if (goalDiff < smallestDiff) {
                smallestDiff = goalDiff;
                teamNameWithMinFAndADiff = row[teamNameIndex];
            }
        }
        return teamNameWithMinFAndADiff;
    }
}
