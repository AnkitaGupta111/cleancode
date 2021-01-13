package com.codingkata.kata2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SoccerLeagueRecord {
    public int goalsScoredFor;
    public int goalsScoredAgainst;
    public String teamName;

    /**
     *
     * @return difference between goalsScoredFor and goalsScoredAgainst team
     */
    public int getDifferenceInGoalsScored(){
        return Math.abs(goalsScoredFor-goalsScoredAgainst);
    }
}
