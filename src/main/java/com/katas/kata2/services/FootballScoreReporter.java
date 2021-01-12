package com.katas.kata2.services;

import com.katas.kata2.pojos.Football;
import com.katas.kata2.services.impl.FootballFileReader;

import java.util.Comparator;

public class FootballScoreReporter {
    private final FileReader<Football> footballFileReader;

    public FootballScoreReporter(FileReader<Football> footballFileReader) {
        this.footballFileReader = footballFileReader;
    }

    public FootballScoreReporter(String filePath) {
        this(new FootballFileReader<>(filePath));
    }

    /**
     * @return Team with smallest goal difference
     */
    public String getLeastDifferenceGoals() {
        return footballFileReader.getFileData()
                .stream()
                .min(Comparator.comparingInt(Football::goalDifference))
                .map(Football::getTeam)
                .orElseThrow();
    }
}
