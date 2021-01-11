package com.katas.kata2;

import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class FootballMatchDataProcessor {
    String fileName;

    List<String[]> dataRows = new ArrayList<>();

    FootballMatchDataProcessor() {
        fileName = "football.dat";
        try {
            readDataFile();
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
    }

    void readDataFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String fileLine;

        while ((fileLine = br.readLine()) != null) {
            if (!fileLine.trim().equals("") && !fileLine.contains("--------")) {
                String line = fileLine.trim();
                String[] colData = line.split("\\s+-*\\s*");
                dataRows.add(colData);
            }
        }
        br.close();
    }

    String getMinDiffInFandATeam() {
        dataRows.remove(0);
        String teamNameWithMinFAndADiff = null;
        int smallestDiff = Integer.MAX_VALUE;
        int FIndex = 6;
        int AIndex = 7;
        int teamNameIndex = 1;

        for (String[] row : dataRows) {
            String forGoalData = row[FIndex];
            String againstGoalData = row[AIndex];

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
