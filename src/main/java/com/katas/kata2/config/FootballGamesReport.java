package com.katas.kata2.config;

import com.katas.kata2.product.FileHandler;
import com.katas.kata2.product.football.TeamData;
import com.katas.kata2.product.football.FootballFileHandler;

import java.util.Comparator;

public class FootballGamesReport {
    private final FileHandler<TeamData> iFileHandler;

    public FootballGamesReport(String file) {
        this(new FootballFileHandler(file));
    }

    private FootballGamesReport(FileHandler fileHandler) {
        iFileHandler = fileHandler;
    }

    /**
     * method to get the least difference between goals scored for and against
     * @return least difference
     */
    public String getSmallestDifferenceGoals() {
        return iFileHandler.getFileData()
                .stream()
                .min(Comparator.comparingInt(TeamData::getGoalDifference))
                .map(TeamData::getTeamName)
                .orElseThrow();
    }
}
