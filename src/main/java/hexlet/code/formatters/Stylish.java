package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String format(List<Map<String, List<Object>>> diffTree) {
        StringBuilder stylish = new StringBuilder("{\n");
        for (Map<String, List<Object>> element : diffTree) {
            for (Map.Entry<String, List<Object>> flag : element.entrySet()) {
                var key = flag.getValue();
                var value = flag.getValue();
                switch (flag.getKey()) {
                    case "added" -> stylish.append(String.format("  + %s: %s\n", key.get(0), value.get(1)));
                    case "removed" -> stylish.append(String.format("  - %s: %s\n", key.get(0), value.get(1)));
                    case "unchanged" -> stylish.append(String.format("    %s: %s\n", key.get(0), value.get(1)));
                    case "updated" -> {
                        stylish.append(String.format("  - %s: %s\n", key.get(0), value.get(2)));
                        stylish.append(String.format("  + %s: %s\n", key.get(0), value.get(1)));
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + flag.getKey());
                }
            }
        }
        stylish.append("}");
        return stylish.toString().trim();
    }

}
