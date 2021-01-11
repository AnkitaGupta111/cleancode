import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import soccer_league.SoccerLeagueFileAnalysis;
import weather.WeatherFileAnalysis;

@RunWith(JUnit4.class)
public class FileAnalysisTest {
    private final SoccerLeagueFileAnalysis soccerLeagueFileAnalysis = new SoccerLeagueFileAnalysis("/home/ruchitha/Downloads/football.dat");
    private final WeatherFileAnalysis weatherFileAnalysis = new WeatherFileAnalysis("/home/ruchitha/Downloads/weather.dat");

    @Test
    void testSoccerLeagueFileAnalysis() {
        Assertions.assertEquals("Aston_Villa", soccerLeagueFileAnalysis.getSmallestDifferenceGoals());
    }

    @Test
    void testWeatherFileAnalysis() {
        Assertions.assertEquals("14", weatherFileAnalysis.getSmallestDifferenceGoals());
    }
}
