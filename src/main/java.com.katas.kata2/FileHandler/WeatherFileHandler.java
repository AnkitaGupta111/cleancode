package FileHandler;

import weather.Weather;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class WeatherFileHandler implements IFileHandler<Weather> {
    private final String fileName;

    public WeatherFileHandler(String fileName) {
        this.fileName = fileName;
    }

    /**
     * method to get data from the given file
     *
     * @return list of weather objects representing data
     */
    @Override
    public List<Weather> getData() {
        List<Weather> weatherObjects = new ArrayList<>();
        AtomicReference<AtomicReferenceArray<String>> chars = new AtomicReference<>(new AtomicReferenceArray<>(new String[0]));
        Arrays.stream(readFile(fileName)).forEach(line -> {
            chars.set(new AtomicReferenceArray<>(line.replaceAll("[*]", "").split("\\s+")));
            if (!line.isBlank() && !line.contains("Dy"))
                weatherObjects.add(new Weather(Double.parseDouble(chars.get().get(2)), Double.parseDouble(chars.get().get(3)), chars.get().get(1)));
        });
        return weatherObjects;
    }
}
