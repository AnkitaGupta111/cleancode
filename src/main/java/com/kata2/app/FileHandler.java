package com.kata2.app;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class handles the reading data in a file.
 */
public class FileHandler {

    public List<String> getFileData(final String fileName, String[] skipLineWithStartingWords) {
        File file = new File(fileName);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            return bufferedReader.lines().filter(line -> !skipLine(line, skipLineWithStartingWords)).collect(Collectors.toList());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean skipLine(String line, String[] skipLineWithStartingWords) {
        for(String word : skipLineWithStartingWords) {
            if(line.trim().startsWith(word) || line.trim().isEmpty()) {
                return true;
            }
        }
        return false;
    }

}
