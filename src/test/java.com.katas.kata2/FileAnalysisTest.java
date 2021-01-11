import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import soccer_league.SoccerLeagueFileAnalysis;
import weather.WeatherFileAnalysis;

@ExtendWith(SpringExtension.class)
public class FileAnalysisTest {
    private final SoccerLeagueFileAnalysis soccerLeagueFileAnalysis = new SoccerLeagueFileAnalysis("src/main/resources/football.dat");
    private final WeatherFileAnalysis weatherFileAnalysis = new WeatherFileAnalysis("src/main/resources/weather.dat");

    @Test
    void testSoccerLeagueFileAnalysis() {
        Assertions.assertEquals("Aston_Villa", soccerLeagueFileAnalysis.getSmallestDifferenceGoals());
    }

    @Test
    void testWeatherFileAnalysis() {
        Assertions.assertEquals("14", weatherFileAnalysis.getSmallestDifferenceGoals());
    }
}
