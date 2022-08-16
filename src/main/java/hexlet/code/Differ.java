package hexlet.code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath)
            throws IOException {
        Map<String, Object> map1 = Parser.parser(firstFilePath);
        Map<String, Object> map2 = Parser.parser(secondFilePath);
        Map<String, Object> resultMap = new LinkedHashMap<>();
        var allKeys = getSortedKeys(map1, map2);
        for (String key : allKeys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                resultMap.put("- " + key, map1.get(key));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                resultMap.put("+ " + key, map2.get(key));
            } else if (map1.get(key) == null && map2.get(key) == null) {
                resultMap.put("  " + key, map1.get(key));
            } else if ((map1.get(key) != null && map2.get(key) != null) && (map1.get(key).equals(map2.get(key)))) {
                resultMap.put("  " + key, map1.get(key));
            } else {
                resultMap.put("- " + key, map1.get(key));
                resultMap.put("+ " + key, map2.get(key));
            }
        }
        return printDiff(resultMap);

    }

    private static List<String> getSortedKeys(Map<String, Object> firstMap, Map<String, Object> secondMap) {
        Set<String> keys1 = new HashSet<>();
        Set<String> keys2 = new HashSet<>();
        for (Map.Entry<String, Object> pair : firstMap.entrySet()) {
            keys1.add(pair.getKey());
        }
        for (Map.Entry<String, Object> pair : secondMap.entrySet()) {
            keys2.add(pair.getKey());
        }
        keys1.addAll(keys2);
        List<String> allKeys = new ArrayList<>(keys1);
        Collections.sort(allKeys);
        return allKeys;
    }

    private static String printDiff(Map<String, Object> map) {
        return map.keySet().stream()
                .map(key -> "  " + key + ": " + map.get(key) + "\n")
                .collect(Collectors.joining("", "{" + "\n", "}"));
    }
}
