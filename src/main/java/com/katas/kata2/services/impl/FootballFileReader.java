package com.katas.kata2.services.impl;

import com.katas.kata2.pojos.Football;
import com.katas.kata2.services.FileReader;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@Service
public class FootballFileReader<T> implements FileReader<T> {
    private static final String HEADING = "Team";
    private final String filePath;

    @Override
    public boolean skipLine(String line) {
        return line.trim().startsWith(HEADING) || line.isBlank() || line.contains("----------");
    }

    @Override
    public List getFileData() {
        List<Football> matches;
        matches = readFile(filePath)
                .stream()
                .filter(line -> !skipLine(line))
                .map(this::getTeamDataFromFileLine)
                .collect(Collectors.toList());
        return matches;
    }

    /**
     * Extracts football pojo for each line
     *
     * @param lineItem Each row values
     * @return Football pojo with all the values set.
     */
    private Football getTeamDataFromFileLine(String lineItem) {
        Football football = new Football();

        String team = lineItem.substring(7, 22).trim();
        football.setTeam(team);

        int goalsScored = Integer.parseInt(lineItem.substring(40, 47).trim());
        football.setGoalsScored(goalsScored);

        int goalsConceived = Integer.parseInt(lineItem.substring(49, 56).trim());
        football.setGoalsConceived(goalsConceived);

        return football;
    }
}
