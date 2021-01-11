package com.cleancode.datamunging.models;

import com.cleancode.datamunging.interfaces.ISourceData;
import com.cleancode.datamunging.interfaces.ISourceDataFactory;
import com.cleancode.datamunging.interfaces.ISourceDataParams;
import com.cleancode.datamunging.models.enums.LeagueAttributesEnum;

import java.util.ArrayList;
import java.util.List;

public class LeagueDataFactory implements ISourceDataFactory  {

    static String regex = "^\\s+\\d+.\\s+(\\w+)\\s+\\d+\\s+\\d+\\s+\\d+\\s+\\d+\\s+(\\d+)\\s+-\\s+(\\d+)+\\s+\\d+$";

    @Override
    public List<ISourceDataParams> getDataParams() {
        return new ArrayList<ISourceDataParams>() {{
            add(new DataParams(1, String.valueOf(LeagueAttributesEnum.TEAM_NAME)));
            add(new DataParams(2, String.valueOf(LeagueAttributesEnum.FOR_GOALS)));
            add(new DataParams(3, String.valueOf(LeagueAttributesEnum.AGAINST_GOALS)));
        }};
    }

    @Override
    public ISourceData create(List<ISourceDataParams> params) {
        String teamName = "";
        int forGoals = -1, againstGoals = -1;

        for(ISourceDataParams param: params) {
            switch (LeagueAttributesEnum.valueOf(param.getName())) {
                case TEAM_NAME:
                    teamName = (String) param.getValue();
                    break;
                case FOR_GOALS:
                    forGoals= Integer.parseInt((String) param.getValue());
                    break;
                case AGAINST_GOALS:
                    againstGoals = Integer.parseInt((String) param.getValue());
                    break;
            }
        }
        return ImmutableLeagueData.builder()
                .teamName(teamName)
                .forGoals(forGoals)
                .againstGoals(againstGoals)
                .build();
    }

    @Override
    public String getRegex() {
        return regex;
    }
}
