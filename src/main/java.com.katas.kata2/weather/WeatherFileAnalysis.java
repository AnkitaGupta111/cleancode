package weather;

import FileHandler.IFileHandler;
import FileHandler.WeatherFileHandler;

import java.util.Comparator;


public class WeatherFileAnalysis {

    private final IFileHandler<Weather> iFileHandler;

    public WeatherFileAnalysis(String file) {

        iFileHandler = new WeatherFileHandler(file);
    }

    /**
     * method to get day number  with smallest difference in minimum and maximum temperature
     *
     * @return day number
     */
    public String getSmallestDifferenceGoals() {
        return iFileHandler.getData()
                .stream()
                .min(Comparator.comparingDouble(Weather::getTempDifference))
                .map(Weather::getDayNumber)
                .orElseThrow();
    }
}
