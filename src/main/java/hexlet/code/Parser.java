package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parser(String filePath)
            throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> map = mapper.readValue(getFileReader(Path.of(filePath)), new TypeReference<>() {
        });
        return map;
    }

    private static FileReader getFileReader(Path path) throws FileNotFoundException {
        return new FileReader(path.toFile());
    }
}
