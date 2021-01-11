package com.katas.kata2.part3;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;


@Slf4j
@Getter
public class FileDataHandler {
    String fileName;
    List<String[]> dataRows = new ArrayList<>();

    FileDataHandler(String fileName) throws IOException {
        this.fileName = fileName;
        try {
            readDataFile();
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
            throw new IOException(ioException.getMessage());

        }
    }

    /**
     * read file and store data in the form of list of string array
     *
     * @throws IOException when file provided not found
     */
    private void readDataFile() throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        String fileLine;

        while ((fileLine = br.readLine()) != null) {
            if (!fileLine.trim().equals("") && !fileLine.contains("--------")) {
                String line = fileLine.trim();
                String[] colData = line.split("\\s+-*\\s*");
                dataRows.add(colData);
            }
        }
        br.close();
    }

    /**
     * it return only necessary data based on arguments for operations
     *
     * @param rowsNumToSkip     list of row number which are to be skip
     * @param colIndexToInclude list of column indexes required for operation
     * @return required data set for operation
     */
    List<String[]> getRequiredDataSet(List<Integer> rowsNumToSkip, List<Integer> colIndexToInclude) {
        AtomicInteger index = new AtomicInteger();
        return dataRows.stream().filter((row) -> !rowsNumToSkip.contains(index.getAndIncrement() + 1)).map((row) -> getRequiredColArray(row, colIndexToInclude)).collect(Collectors.toList());
    }

    /**
     * it gives required col list for a particular row
     *
     * @param row        data row whose column to return
     * @param colIndexes list of col indexes required
     * @return required col list bases on list of col indexes
     */
    private String[] getRequiredColArray(String[] row, List colIndexes) {
        AtomicInteger index = new AtomicInteger();
        return Arrays.stream(row).filter(field -> colIndexes.contains(index.getAndIncrement())).toArray(String[]::new);
    }
}
