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
        Map<String, Object> firstMap = Parser.parser(firstFilePath);
        Map<String, Object> secondMap = Parser.parser(secondFilePath);
        Map<String, Object> map = new LinkedHashMap<>();
        var allKeys = getSortedKeys(firstMap, secondMap);
        for (String key : allKeys) {
            if (firstMap.containsKey(key) && !secondMap.containsKey(key)) {
                map.put("- " + key, firstMap.get(key));
            } else if (!firstMap.containsKey(key) && secondMap.containsKey(key)) {
                map.put("+ " + key, secondMap.get(key));
            } else if (firstMap.containsKey(key) && secondMap.containsKey(key)) {
                if (!firstMap.get(key).equals(secondMap.get(key))) {
                    map.put("- " + key, firstMap.get(key));
                    map.put("+ " + key, secondMap.get(key));
                } else {
                    map.put("  " + key, secondMap.get(key));
                }
            } else {
                map.put("  " + key, secondMap.get(key));
            }
        }
        return printDiff(map);

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
