package com.katas.kata2.product.weather;

public class Day {
    private final Double maxTemp;
    private final Double minTemp;
    private final int dayNumber;

    public Day(int dayNumber, Double maxTemp, Double minTemp) {
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
        this.dayNumber = dayNumber;
    }

    public Double getTempDifference() {
        return Double.sum(maxTemp, -minTemp);
    }

    public Integer getDayNumber() {
        return dayNumber;
    }
}
