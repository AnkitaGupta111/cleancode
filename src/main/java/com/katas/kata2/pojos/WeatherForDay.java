package com.katas.kata2.pojos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class WeatherForDay {
    private Double maxTemp;
    private Double minTemp;
    private int dayNumber;

    public Double getTemperatureDifference() {
        return maxTemp - minTemp;
    }
}
