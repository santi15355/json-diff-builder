package hexlet.code.formatters;

import java.util.Collection;
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
                    case "unchanged" -> {
                        continue;
                    }
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
                    default -> throw new IllegalStateException("Unexpected value: " + flag.getKey());

                }
            }
        }
        return plain.toString().trim();
    }

    private static String printObject(Object obj) {
        return obj instanceof Object[] || obj instanceof Collection || obj instanceof Map
                ? "[complex value]"
                : obj instanceof String
                ? "'" + obj + "'"
                : String.valueOf(obj);
    }
}
