package com.codingkata.kata2.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class SoccerLeagueRecord {
    private String teamName;
    private int goalsScoredFor;
    private int goalsScoredAgainst;
    /**
     *
     * @return difference between goalsScoredFor and goalsScoredAgainst team
     */
    public int getDifferenceInGoalsScored(){
        return Math.abs(goalsScoredFor-goalsScoredAgainst);
    }
}
