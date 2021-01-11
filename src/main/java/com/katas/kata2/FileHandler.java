package com.katas.kata2;

import lombok.extern.slf4j.Slf4j;
import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
public class FileHandler {
    /**
     * filtering input file by skipping the lines with the mentioned starting words and return filtered lines list
     *
     * @param fileName                  name of the file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return filtered lines list
     */
    public List<String> getFilteredFileData(final String fileName, String[] skipLineWithStartingWords) {
        Path path = Paths.get(fileName);
        try {
            BufferedReader reader = Files.newBufferedReader(path);
            return reader.lines().filter(line -> !skipLine(line, skipLineWithStartingWords)).collect(Collectors.toList());
        } catch (IOException e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * checks the lines with mentioned starting words and empty lines
     *
     * @param line                      input line from the file
     * @param skipLineWithStartingWords String array containing words to skip lines
     * @return boolean value for the skipLine method
     */
    private boolean skipLine(String line, String[] skipLineWithStartingWords) {
        return Arrays.stream(skipLineWithStartingWords).anyMatch(word -> line.trim().startsWith(word) || line.trim().isEmpty());
    }
}
