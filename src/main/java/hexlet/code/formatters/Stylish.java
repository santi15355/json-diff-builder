package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

public class Stylish {

    public static String format(List<Map<String, List<Object>>> diffTree) {
        StringBuilder stylish = new StringBuilder("{\n");
        for (Map<String, List<Object>> element : diffTree) {
            for (Map.Entry<String, List<Object>> flag : element.entrySet()) {
                switch (flag.getKey()) {
                    case "added" -> stylish.append("  + ").append(flag.getValue().get(0)).append(":")
                            .append(flag.getValue().get(1)).append("\n");
                    case "removed" -> stylish.append("  - ").append(flag.getValue().get(0)).append(": ")
                            .append(flag.getValue().get(1)).append("\n");
                    case "unchanged" -> stylish.append("    ").append(flag.getValue().get(0)).append(": ")
                            .append(flag.getValue().get(1)).append("\n");
                    case "updated" -> {
                        stylish.append("  - ").append(flag.getValue().get(0)).append(": ")
                                .append(flag.getValue().get(2)).append("\n");
                        stylish.append("  + ").append(flag.getValue().get(0)).append(": ")
                                .append(flag.getValue().get(1)).append("\n");
                    }
                    default -> throw new IllegalStateException("Unexpected value: " + flag.getKey());
                }
            }
        }
        stylish.append("}");
        return stylish.toString().trim();
    }

}
