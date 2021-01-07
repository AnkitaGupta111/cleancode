package com.katas.kata2.product.football;

import com.katas.kata2.product.FileHandler;

import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the data of football file
 */
public class FootballFileHandler<T> implements FileHandler<T> {

    private static final String HEADER = "Team";
    private final String fileName;

    public FootballFileHandler(String file) {
        fileName = file;
    }

    @Override
    public List getFileData() {
        List games;
        games = readFile(fileName)
                .stream()
                .filter(line -> !skipLine(line))
                .map(line -> getTeamDataFromFileLine(line))
                .collect(Collectors.toList());
        return games;
    }

    @Override
    public boolean skipLine(String line) {
        return line.contains("----------") || line.trim().startsWith(HEADER) || line.isBlank();
    }

    /**
     * method to get the team data from particular line
     * @param line
     * @return TeamData
     */
    private TeamData getTeamDataFromFileLine(String line) {
        int goalsScoredFor = Integer.parseInt(line.substring(40, 47).trim());
        int goalsScoredAgainst = Integer.parseInt(line.substring(49, 56).trim());
        String teamName = line.substring(7, 22).trim();
        return new TeamData(goalsScoredFor, goalsScoredAgainst, teamName);
    }
}

