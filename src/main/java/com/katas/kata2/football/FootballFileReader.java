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
     * @param fileName                  name of the football.dat file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return FootballData list containing teamName, goalsScoredForTheTeam, goalsScoredAgainstTheTeam
     */
    private List<FootballData> getRequiredTeamData(String fileName, String[] skipLineWithStartingWords) {
        List<FootballData> data = new ArrayList<>();
        getFilteredFileData(fileName, skipLineWithStartingWords).stream().forEach(rowData -> {
            String teamName = rowData.trim().substring(3, 18).trim();
            int goalsScoredForTheTeam = Integer.parseInt(rowData.trim().substring(39, 42).trim());
            int goalsScoredAgainstTheTeam = Integer.parseInt(rowData.trim().substring(46, 49).trim());
            data.add(new FootballData(teamName, goalsScoredForTheTeam, goalsScoredAgainstTheTeam));
        });
        return data;
    }

    /**
     * calculates the minimum goals difference and mapping to the respective team
     *
     * @param fileName                  name of the football.dat file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return team name with minimumGoalDifference
     */
    public String getMinimumGoalDifference(final String fileName, final String[] skipLineWithStartingWords) {
        return getRequiredTeamData(fileName, skipLineWithStartingWords)
                .stream()
                .min(Comparator.comparingDouble(FootballData::getGoalsDifference))
                .map(FootballData::getTeamName)
                .orElseThrow(null);
    }
}
