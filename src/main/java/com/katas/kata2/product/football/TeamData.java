package com.katas.kata2.product.football;

public class TeamData {
    private final int goalsScoredFor;
    private final int goalsScoredAgainst;
    private final String teamName;

    public TeamData(int goalsScoredFor, int goalsScoredAgainst, String teamName) {
        this.goalsScoredFor = goalsScoredFor;
        this.goalsScoredAgainst = goalsScoredAgainst;
        this.teamName = teamName;
    }

    public Integer getGoalDifference() {
        return Integer.valueOf(Math.abs(Integer.sum(goalsScoredFor, -goalsScoredAgainst)));
    }

    public String getTeamName() {
        return teamName;
    }
}
