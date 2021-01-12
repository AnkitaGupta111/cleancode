package com.kata2.app.football;

import com.kata2.app.FileHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FootballFileReader extends FileHandler {

    private List<FootballData> getRequiredTeamData(final String fileName, final String[] skipLineWithStartingWords) throws Exception {
        List<FootballData> data = new ArrayList<>();
        for(String rowData : getFileData(fileName, skipLineWithStartingWords)) {
            String teamName = rowData.split(" +")[2];
            int goalScoredFor = Integer.parseInt(rowData.split(" +")[7]);
            int goalScoredAgainst = Integer.parseInt(rowData.split(" +")[9]);
            data.add(new FootballData(teamName, goalScoredFor, goalScoredAgainst));
        }
        return data;
    }

    public String getSmallestGoalDifference(final String fileName, final String[] skipLineWithStartingWords) throws Exception {
        return getRequiredTeamData(fileName, skipLineWithStartingWords)
                .stream()
                .min(Comparator.comparingDouble(FootballData::getGoalDifference))
                .map(FootballData::getTeamName)
                .orElseThrow(null);
    }
}
