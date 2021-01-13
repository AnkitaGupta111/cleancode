package com.katas.kata2.services;


import com.katas.kata2.Kata2Application;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Interface to read data from .dat file.
 */
public interface FileReader<T> {
    // Sl4j is not supported for interfaces. Hence using default instantiation of logger
    Logger logger = LoggerFactory.getLogger(FileReader.class);

    boolean skipLine(String line);

    List<T> getFileData();

    default List<String> readFile(String filePath) {
        ClassLoader classLoader = Kata2Application.class.getClassLoader();
        URL url = classLoader.getResource(filePath);
        if (url != null) {
            File file = new File(url.getFile());
            try {
                return Files.readAllLines(file.toPath());
            } catch (IOException exception) {
                logger.error("Something went wrong while reading file at path {}", filePath, exception);
            }
        }
        return new ArrayList<>();
    }

}