package com.codingkata.kata2.FileHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * reading data from file
 * @param <T> datatype
 */

public interface FileHandlerInterface<T> {
    /**
     *
     * @return list of objects of respective data
     */
    List<T> getFileData();

    /**
     *
     * @param filepath  path of the file
     * @return lines in the file
     */
    default List<String> readFile(String filepath){
        List<String> fileDataList=new ArrayList<>();
        Logger log= LoggerFactory.getLogger(FileHandlerInterface.class);
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filepath));
            fileDataList = bufferedReader.lines().collect(Collectors.toList());
        }
        catch (IOException exception){
            log.error(exception.getMessage());
        }
        return fileDataList;
    }
}
