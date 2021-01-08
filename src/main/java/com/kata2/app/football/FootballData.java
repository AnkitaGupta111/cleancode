package com.kata2.app.football;

public class FootballData {
    private final String teamName;
    private final int goalScoredFor;
    private final int goalScoredAgainst;

    public FootballData(String teamName, int goalScoredFor, int goalScoredAgainst) {
        this.teamName = teamName;
        this.goalScoredFor = goalScoredFor;
        this.goalScoredAgainst = goalScoredAgainst;
    }

    public String getTeamName() {
        return teamName;
    }

    public int getGoalScoredFor() {
        return goalScoredFor;
    }

    public int getGoalScoredAgainst() {
        return goalScoredAgainst;
    }

    public int getGoalDifference() {
        return Math.abs(goalScoredFor - goalScoredAgainst);
    }
}
