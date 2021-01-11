package com.cleancode.datamunging;

import com.cleancode.datamunging.interfaces.ISourceData;
import com.cleancode.datamunging.interfaces.ISourceDataFactory;
import com.cleancode.datamunging.interfaces.ISourceDataParams;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class DataService {

    private Logger logger = Logger.getLogger(DataService.class);

    public void importData(File file, List<ISourceData> list, ISourceDataFactory iSourceDataFactory) {
        Pattern pattern = Pattern.compile(iSourceDataFactory.getRegex());
        try (Stream<String> lines = Files.lines(Paths.get(file.getAbsolutePath()))) {
            lines.forEachOrdered(line -> {
                Optional<ISourceData> iSourceData = process(line, pattern, iSourceDataFactory);
                iSourceData.ifPresent(list::add);
            });
        } catch (IOException ex) {
            logger.error("Error opening the data source file ", ex);
        }
    }

    public Optional<ISourceData> process(String line, Pattern pattern, ISourceDataFactory iSourceDataFactory) {
        Matcher matcher = pattern.matcher(line);
        List<ISourceDataParams> iSourceDataParams = iSourceDataFactory.getDataParams();
        if(matcher.find()) {
            for(ISourceDataParams params: iSourceDataParams) {
                params.setValue(matcher.group(params.getId()));
            }
            return Optional.of(iSourceDataFactory.create(iSourceDataParams));
        }
        return Optional.empty();
    }
}
