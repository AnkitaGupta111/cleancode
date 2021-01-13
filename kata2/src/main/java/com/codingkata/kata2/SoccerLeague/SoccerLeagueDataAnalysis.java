package com.codingkata.kata2.SoccerLeague;

import com.codingkata.kata2.Entity.SoccerLeagueRecord;
import com.codingkata.kata2.FileHandler.FileHandlerInterface;

import java.util.Comparator;

public class SoccerLeagueDataAnalysis {
    private final FileHandlerInterface<SoccerLeagueRecord> fileHandlerInterface;

    public SoccerLeagueDataAnalysis(String file) {
        fileHandlerInterface = new SoccerLeagueFileHandler(file);
    }

    /**
     *
     * @return smallest difference in for and against goals
     */
    public String getSmallestGoalDifference(){
        return fileHandlerInterface.getFileData()
                .stream()
                .min(Comparator.comparingInt(SoccerLeagueRecord::getDifferenceInGoalsScored))
                .map(SoccerLeagueRecord::getTeamName)
                .orElseThrow();
    }
}
