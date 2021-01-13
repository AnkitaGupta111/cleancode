package com.codingkata.kata2.weather;

import com.codingkata.kata2.Entity.WeatherRecord;
import com.codingkata.kata2.FileHandler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class WeatherDataHandler extends FileHandler {
//    void readFile(String filepath) throws IOException {
//        Scanner scanner=new Scanner(new File(filepath));
//        scanner.nextLine();
//        List<WeatherRecord> weatherRecordList=new ArrayList<>();
//        while (scanner.hasNext()){
//            String day=scanner.next().replace('*', ' ').trim();
//            String min=scanner.next().replace('*', ' ').trim();
//            String max=scanner.next().replace('*', ' ').trim();
//            scanner.nextLine();
//            WeatherRecord weatherRecord=new WeatherRecord(Integer.parseInt(day),Double.parseDouble(min),Double.parseDouble(max));
//            weatherRecordList.add(weatherRecord);
//        }
//        for (WeatherRecord w:weatherRecordList){
//            System.out.println(w.getDayOfMonth()+"**"+w.getMaximumDailyTemperature()+"--"+w.getMinimumDailyTemperature());
//        }
//
//    }

    private List<WeatherRecord> getWeatherData(final String filepath){
        List<WeatherRecord> weatherRecordList=new ArrayList<>();
        getFileData(filepath).stream().forEach(weatherRow->{
//           int day=Integer.parseInt(weatherRow.trim().substring(0,3).trim());
//           double max=Double.parseDouble(weatherRow.trim().substring(3,7).trim().replaceAll("[^0-9]", ""));
//           double min=Double.parseDouble(weatherRow.trim().substring(8,12).trim().replaceAll("[^0-9]", ""));
//            weatherRow.r
//            Stream<String> colData= Arrays.stream(weatherRow.split("\\s\\s"));
////            String[] colData= (String[]) weatherRow.lines().toArray();
//                colData.forEach(s -> {
//                    s.replace('*', ' ').trim();
//                    System.out.println(s);
//                });
            Stream<String> lines= weatherRow.lines();
            lines.forEach(s -> {
                String str[]=s.split("\\s\\s");
                s.trim().replace('*', ' ').trim();
                System.out.println(s);
            });
        });
        return weatherRecordList;
    }
    public static void main(String args[]) throws IOException {
        WeatherDataHandler weatherData=new WeatherDataHandler();

        List<WeatherRecord> weatherRecordList=weatherData.getWeatherData("src/main/resources/weather.dat");
        for (WeatherRecord w:weatherRecordList){
            System.out.println(w.getDayOfMonth()+"**"+w.getMinimumDailyTemperature()+" --"+ w.getMaximumDailyTemperature());
        }
    }
}
