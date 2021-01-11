package FileHandler;

import soccer_league.SoccerLeague;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicReferenceArray;

public class SoccerLeagueFileHandler implements IFileHandler<SoccerLeague> {
    private final String fileName;

    public SoccerLeagueFileHandler(String fileName) {
        this.fileName = fileName;
    }

    /**
     * method to get data from the given file
     *
     * @return list of soccer league objects representing data
     */
    @Override
    public List<SoccerLeague> getData() {
        List<SoccerLeague> socerLeagues = new ArrayList<>();
        AtomicReference<AtomicReferenceArray<String>> chars = new AtomicReference<>(new AtomicReferenceArray<>(new String[0]));
        Arrays.stream(readFile(fileName)).forEach(line -> {
            chars.set(new AtomicReferenceArray<>(line.split("\\s+")));
            if (!line.contains("----------") && !line.contains("Team")) {
                socerLeagues.add(new SoccerLeague(Integer.parseInt(chars.get().get(7)), Integer.parseInt(chars.get().get(9)), chars.get().get(2)));
            }
        });
        return socerLeagues;
    }
}
