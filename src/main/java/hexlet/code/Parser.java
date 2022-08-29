package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> parser(String filePath, String extension)
            throws IOException {
        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());

        return switch (extension) {
            case "json" -> jsonMapper.readValue(filePath, new TypeReference<>() {
            });
            case "yml", "yaml" -> ymlMapper.readValue(filePath, new TypeReference<>() {
            });
            default -> throw new RuntimeException("File extension not detected");
        };
    }

    private static FileReader getFileReader(Path path) throws FileNotFoundException {
        return new FileReader(path.toFile());
    }
}
