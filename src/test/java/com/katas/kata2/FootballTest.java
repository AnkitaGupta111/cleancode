package com.katas.kata2;

import com.katas.kata2.services.FootballScoreReporter;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FootballTest {
    @Test
    void footballTest() {
        FootballScoreReporter footballScoreReporter = new FootballScoreReporter("football.dat");
        assertEquals("Aston_Villa", footballScoreReporter.getLeastDifferenceGoals());
    }
}
