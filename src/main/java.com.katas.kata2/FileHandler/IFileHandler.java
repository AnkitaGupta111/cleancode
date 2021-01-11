package FileHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


/**
 * declares methods to read given file and get data
 *
 * @param <T> data type
 */
public interface IFileHandler<T> {
    /**
     * method to get data from the given file
     *
     * @return list of objects representing data
     */
    List<T> getData();

    /**
     * to read lines from given file
     *
     * @param filePath file path
     * @return lines in the given file in the form of string array
     */
    default String[] readFile(String filePath) {
        Path path = Paths.get(filePath);
        Logger log = LoggerFactory.getLogger(IFileHandler.class);
        try {
            return Files.readAllLines(path).toArray(new String[0]);
        } catch (IOException ioException) {
            log.error(ioException.getMessage());
        }
        return null;
    }
}
