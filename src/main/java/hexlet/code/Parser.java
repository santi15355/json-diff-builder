package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parser(String data, String extension)
            throws Exception {

        return switch (extension) {
            case "json" -> new ObjectMapper().readValue(data, new TypeReference<>() {
            });
            case "yml", "yaml" -> new ObjectMapper(new YAMLFactory()).readValue(data, new TypeReference<>() {
            });
            default -> throw new RuntimeException("File extension not detected");
        };
    }
}
