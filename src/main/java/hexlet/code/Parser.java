package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.util.Map;

public class Parser {

    public static Map<String, Object> parser(String filePath, String extension)
            throws Exception {
        ObjectMapper jsonMapper = new ObjectMapper();
        ObjectMapper ymlMapper = new ObjectMapper(new YAMLFactory());

        if (extension.endsWith(".json")) {
            return jsonMapper.readValue(filePath, new TypeReference<>() {
            });
        }
        if (extension.endsWith(".yml") | filePath.endsWith(".yaml")) {
            return ymlMapper.readValue(filePath, new TypeReference<>() {
            });
        } else {
            throw new RuntimeException("File extension not detected");
        }
    }
}
