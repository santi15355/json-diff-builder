package hexlet.code.formatters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Plain {
    public static String format(List<Map<String, List<Object>>> diffTree) {
        StringBuilder plain = new StringBuilder();
        for (Map<String, List<Object>> element : diffTree) {
            for (Map.Entry<String, List<Object>> flag : element.entrySet()) {
                var key = flag.getValue();
                var value = flag.getValue();
                switch (flag.getKey()) {
                    case "added" -> plain.append("Property ").append(printObject(key.get(0)))
                            .append(" was added with value: ")
                            .append(printObject(value.get(1))).append("\n");
                    case "removed" -> plain.append("Property ").append(printObject(key.get(0)))
                            .append(" was removed").append("\n");
                    case "updated" -> {
                        plain.append("Property ").append(printObject(key.get(0)))
                                .append(" was updated. From ")
                                .append(printObject(value.get(2))).append(" to ")
                                .append(printObject(value.get(1))).append("\n");
                    }
                    default -> {
                    }
                }
            }
        }
        return plain.toString().trim();
    }

    private static Object printObject(Object obj) {
        if (obj instanceof Integer || obj == null || obj instanceof Boolean) {
            return obj;
        } else if (obj instanceof ArrayList || obj instanceof Map) {
            return "[complex value]";
        } else {
            return "'" + obj + "'";
        }
    }
}
