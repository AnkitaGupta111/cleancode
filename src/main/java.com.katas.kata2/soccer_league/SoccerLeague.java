package soccer_league;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SoccerLeague {
    public int goalsScoredFor;
    public int goalsScoredAgainst;
    private final String teamName;

    /**
     * method to calculate difference between goals scored for and goals scored against
     *
     * @return difference
     */
    public Integer getGoalDifference() {
        return Math.abs(goalsScoredFor - goalsScoredAgainst);
    }
}
