package example.kata02;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class dto {
    String identifier;
    double diff;
}

public class DataReader {
    String path = "/home/pavan/Desktop/katas/weather.dat";
    List<String> specialChars = new ArrayList<>();
    int opIndex = 0;
    int dIndex1 = 1;
    int dIndex2 = 2;
    boolean ignoreHeader = true;

    public static void main(String[] args) throws IOException {
        DataReader dr = new DataReader();
        dr.readData();
    }


    void readData() throws IOException {
        int skipValue = 0;
        if(ignoreHeader) skipValue = 1;
        specialChars.add("*");
        String data = FileUtils.readFileToString(new File(path),"utf-8");
        Arrays.stream(data.split("\n"))
                .map(row -> row.trim())
                .filter(row -> row.length() > 0)
                .map(row -> removeSpecialChars(row))
                .filter(row -> row.trim().length() > 0)
                .skip(skipValue)
                .map(row -> row.split(" "))
                .map(arr -> Arrays.stream(arr).map(a -> a.trim()).filter(a -> a.length() > 0).collect(Collectors.toList()))
                .map(list -> createDto(list))
                .min((d1,d2) -> {
                    if(d1.diff - d2.diff > 0) {
                        return 1;
                    }
                    if(d1.diff - d2.diff == 0) {
                        return 0;
                    }
                    return -1;
                })
                .ifPresent(dto -> {
                    System.out.println(dto.identifier + "---" + dto.diff);
                });
    }

    String removeSpecialChars(String row) {
        for (String specialChar : specialChars) {
            row = row.replace(specialChar,"");
        }
        return row;
    }

    dto createDto(List<String> list) {
        dto d = new dto();
        d.identifier = list.get(opIndex);
        d.diff = Math.abs(Double.parseDouble(list.get(dIndex1)) - Double.parseDouble(list.get(dIndex2)));
        return d;
    }
}
