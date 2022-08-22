package hexlet.code.formatters;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.List;
import java.util.Map;

public class Json {
    public static String format(List<Map<String, List<Object>>> diffTree) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(diffTree);
    }
}
