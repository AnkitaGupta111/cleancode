package com.katas.kata2.pojos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Football {
    private String team;
    private int goalsScored;
    private int goalsConceived;

    public int goalDifference() {
        return Math.abs(goalsScored - goalsConceived);
    }
}
