package com.katas.kata2.football;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FootballData {
    private final String teamName;
    private final int goalsScoredForTheTeam;
    private final int goalsScoredAgainstTheTeam;

    /**
     * returns the difference goals scored for and goals scored against
     *
     * @return goals-difference
     */
    public int getGoalsDifference() {
        return Math.abs(goalsScoredForTheTeam - goalsScoredAgainstTheTeam);
    }
}
