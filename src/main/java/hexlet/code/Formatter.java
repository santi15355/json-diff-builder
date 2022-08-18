package hexlet.code;

import hexlet.code.formatters.Stylish;

import java.util.List;
import java.util.Map;

public class Formatter {
    public static String format(List<Map<String, List<Object>>> diffTree, String format) throws Exception {

        return switch (format) {
            case "stylish" -> Stylish.format(diffTree);
            default -> throw new RuntimeException("incorrect format: " + format);
        };
    }
}
