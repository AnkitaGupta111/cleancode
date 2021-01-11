package weather;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Weather {
    private final Double maxTemperature;
    private final Double minTemperature;
    private final String dayNumber;

    /**
     * method to return difference between maximum and minimum temperature
     *
     * @return difference
     */
    public Double getTempDifference() {
        return (maxTemperature - minTemperature);
    }
}
