package com.katas.kata2.football;

import com.katas.kata2.FileHandler;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class FootballFileReader extends FileHandler {
    /**
     * returns the list containing teamName, goalsScoredForTheTeam, goalsScoredAgainstTheTeam by trimming
     * the respective substrings from the filtered lines
     *
     * @param filePath                  filePath for football.dat file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return FootballData list containing teamName, goalsScoredForTheTeam, goalsScoredAgainstTheTeam
     */
    private List<FootballData> getRequiredTeamData(String filePath, String[] skipLineWithStartingWords) {
        List<FootballData> data = new ArrayList<>();
        getFilteredFileData(filePath, skipLineWithStartingWords).stream().forEach(rowData -> {
            String[] dataArray = rowData.split("\\s+");
            String teamName = dataArray[2];
            int goalsScoredForTheTeam = Integer.parseInt(dataArray[7]);
            int goalsScoredAgainstTheTeam = Integer.parseInt(dataArray[9]);
            data.add(new FootballData(teamName, goalsScoredForTheTeam, goalsScoredAgainstTheTeam));
        });
        return data;
    }

    /**
     * calculates the minimum goals difference and mapping to the respective team
     *
     * @param filePath                  filePath for the football.dat file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return team name with minimumGoalDifference
     */
    public String getMinimumGoalDifference(final String filePath, final String[] skipLineWithStartingWords) {
        return getRequiredTeamData(filePath, skipLineWithStartingWords)
                .stream()
                .min(Comparator.comparingDouble(FootballData::getGoalsDifference))
                .map(FootballData::getTeamName)
                .orElseThrow();
    }
}
