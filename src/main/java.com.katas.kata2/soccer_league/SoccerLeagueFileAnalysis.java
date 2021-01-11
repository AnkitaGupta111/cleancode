package soccer_league;

import FileHandler.IFileHandler;
import FileHandler.SoccerLeagueFileHandler;

import java.util.Comparator;

public class SoccerLeagueFileAnalysis {
    private final IFileHandler<SoccerLeague> iFileHandler;

    public SoccerLeagueFileAnalysis(String file) {
        iFileHandler = new SoccerLeagueFileHandler(file);
    }

    /**
     * method to get team name with smallest difference in goals
     *
     * @return team name
     */
    public String getSmallestDifferenceGoals() {
        return iFileHandler.getData()
                .stream()
                .min(Comparator.comparingInt(SoccerLeague::getGoalDifference))
                .map(SoccerLeague::getTeamName)
                .orElseThrow();
    }
}
