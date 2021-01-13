package FileHandler;

import soccer_league.SoccerLeague;

import java.util.List;
import java.util.stream.Collectors;

public class SoccerLeagueFileHandler implements IFileHandler<SoccerLeague> {
    private final String filePath;

    public SoccerLeagueFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * method to get data from the given file
     *
     * @return list of soccer league objects representing data
     */
    @Override
    public List<SoccerLeague> getData() {
        List<SoccerLeague> socerLeagues = readFile(filePath)
                .stream()
                .filter(line -> (!line.contains("----------") && !line.contains("Team")))
                .map(line -> getSoccerLeagueObjectFromFileLine(line))
                .collect(Collectors.toList());
        return socerLeagues;
    }

    /**
     * method to get the soccerLeague object from particular line
     *
     * @param line  line in the file
     * @return soccer league object
     */
    private SoccerLeague getSoccerLeagueObjectFromFileLine(String line) {
        String[] lineSubStrings = line.split("\\s+");
        int goalsScoredFor = Integer.parseInt(lineSubStrings[7]);
        int goalsScoredAgainst = Integer.parseInt(lineSubStrings[9]);
        String teamName = lineSubStrings[2];
        return new SoccerLeague(goalsScoredFor, goalsScoredAgainst, teamName);
    }
}
