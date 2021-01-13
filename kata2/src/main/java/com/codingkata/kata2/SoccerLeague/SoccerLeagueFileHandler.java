package com.codingkata.kata2.SoccerLeague;

import com.codingkata.kata2.Entity.SoccerLeagueRecord;
import com.codingkata.kata2.FileHandler.FileHandlerInterface;
import java.util.List;
import java.util.stream.Collectors;

public class SoccerLeagueFileHandler implements FileHandlerInterface<SoccerLeagueRecord> {
    private final String filepath;

    public SoccerLeagueFileHandler(String filepath) {
        this.filepath = filepath;
    }

    /**
     * gets soccerleague data from the file
     * @return list of SoccerLeague objects
     */
    @Override
    public List<SoccerLeagueRecord> getFileData() {
        List<SoccerLeagueRecord> socerLeagues = readFile(filepath)
                .stream()
                .filter(line -> (!line.contains("----------") && !line.contains("Team")))
                .map(line -> getSoccerLeagueRecordFromFile(line))
                .collect(Collectors.toList());
        return socerLeagues;
    }

    /**
     *method to get SoccerLeagueRecord from each line
     * @param line takes each line in the file
     * @return SoccerLeague object
     */
    private SoccerLeagueRecord getSoccerLeagueRecordFromFile(String line){
        String [] dataInLines=line.split("\\s+");
        String teamName=dataInLines[2];
        int goalsScoredFor = Integer.parseInt(dataInLines[7]);
        int goalsScoredAgainst = Integer.parseInt(dataInLines[9]);
        return new SoccerLeagueRecord(teamName,goalsScoredFor,goalsScoredAgainst);
    }
}
