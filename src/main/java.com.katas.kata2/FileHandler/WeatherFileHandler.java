package FileHandler;

import weather.Weather;

import java.util.List;
import java.util.stream.Collectors;

public class WeatherFileHandler implements IFileHandler<Weather> {
    private final String filePath;

    public WeatherFileHandler(String filePath) {
        this.filePath = filePath;
    }

    /**
     * method to get data from the given file
     *
     * @return list of weather objects representing data
     */
    @Override
    public List<Weather> getData() {
        List<Weather> weatherObjects = readFile(filePath)
                .stream()
                .filter(line -> (!line.isBlank() && !line.contains("Dy")))
                .map(line -> getWeatherObjectFromFileLine(line))
                .collect(Collectors.toList());
        return weatherObjects;
    }

    /**
     * method to get the weather object from particular line
     *
     * @param line
     * @return weather object
     */
    private Weather getWeatherObjectFromFileLine(String line) {
        String[] lineSubStrings = line.replaceAll("[]*]", "").split("\\s+");
        double minTemperature = Double.parseDouble(lineSubStrings[3]);
        double maxTemperature = Double.parseDouble(lineSubStrings[2]);
        String day = lineSubStrings[1];
        return new Weather(maxTemperature, minTemperature, day);
    }
}
