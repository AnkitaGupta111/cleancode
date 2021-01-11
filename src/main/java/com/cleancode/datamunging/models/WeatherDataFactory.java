package com.cleancode.datamunging.models;

import com.cleancode.datamunging.interfaces.ISourceData;
import com.cleancode.datamunging.interfaces.ISourceDataFactory;
import com.cleancode.datamunging.interfaces.ISourceDataParams;
import com.cleancode.datamunging.models.enums.WeatherAttributesEnum;

import java.util.ArrayList;
import java.util.List;

public class WeatherDataFactory implements ISourceDataFactory {

    String dataRegex = "^ +(\\d+) +(\\d+) +(\\d+).+$";


    @Override
    public List<ISourceDataParams> getDataParams() {
        return new ArrayList<ISourceDataParams>() {{
            add(new DataParams(1, String.valueOf(WeatherAttributesEnum.DAY)));
            add(new DataParams(2, String.valueOf(WeatherAttributesEnum.MAXTEMP)));
            add(new DataParams(3, String.valueOf(WeatherAttributesEnum.MINTEMP)));
        }};
    }

    @Override
    public ISourceData create(List<ISourceDataParams> params) {
        int day=-1, maxTemp=-1, minTemp=-1;
        for(ISourceDataParams param: params) {
            switch (WeatherAttributesEnum.valueOf(param.getName())) {
                case DAY:
                    day = Integer.parseInt((String) param.getValue());
                    break;
                case MINTEMP:
                    minTemp = Integer.parseInt((String) param.getValue());
                    break;
                case MAXTEMP:
                    maxTemp = Integer.parseInt((String) param.getValue());
                    break;
            }
        }
        return ImmutableWeatherData.builder()
                .day(day)
                .minTemp(minTemp)
                .maxTemp(maxTemp)
                .build();
    }

    @Override
    public String getRegex() {
        return dataRegex;
    }
}
