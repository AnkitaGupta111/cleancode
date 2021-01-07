package com.katas.kata2.product;

import com.katas.kata2.config.Kata2Application;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This interface has methods to get the required data from the file
 */
public interface FileHandler<T> {

    List<T> getFileData();

    default List<String> readFile(String fileName) {
        ClassLoader classLoader = Kata2Application.class.getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());
        Logger log = LoggerFactory.getLogger(FileHandler.class);
        try {
            return Files.readAllLines(file.toPath());
        } catch (IOException exception) {
            log.error(exception.getMessage());
        }
        return null;
    }

    boolean skipLine(String line);


}