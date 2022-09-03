package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parser(String data, String extension)
            throws Exception {

        if (extension.endsWith(".json")) {
            return new ObjectMapper().readValue(data, new TypeReference<>() {
            });
        }
        if (extension.endsWith(".yml") | extension.endsWith(".yaml")) {
            return new ObjectMapper(new YAMLFactory()).readValue(data, new TypeReference<>() {
            });
        } else {
            throw new RuntimeException("File extension not detected");
        }
    }
}
