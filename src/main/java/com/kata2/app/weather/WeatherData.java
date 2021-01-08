package com.kata2.app.weather;

public class WeatherData {
    private final int day;
    private final float maxTemp;
    private final float minTemp;

    public WeatherData(int day, float maxTemp, float minTemp) {
        this.day = day;
        this.maxTemp = maxTemp;
        this.minTemp = minTemp;
    }

    public int getDay() {
        return day;
    }

    public float getMaxTemp() {
        return maxTemp;
    }

    public float getMinTemp() {
        return minTemp;
    }

    public float getTempDifference() {
        return maxTemp - minTemp;
    }


    /*private WeatherData getWeatherDataWithSmallestTempSpread() throws Exception {
        int smallestTemp = 150;
        WeatherData minWeatherData = new WeatherData(1, "80", "40", 60);
        for(WeatherData weatherData : changeTextDataToListData()) {
            if(weatherData.getAvgTemp() <= smallestTemp) {
                smallestTemp = weatherData.getAvgTemp();
                minWeatherData = weatherData;
            }
        }
        return minWeatherData;
    }*/
}
