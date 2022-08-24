package hexlet.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format)
            throws Exception {
        Map<String, Object> map1 = Parser.parser(firstFilePath);
        Map<String, Object> map2 = Parser.parser(secondFilePath);
        List<Map<String, List<Object>>> diffList = new ArrayList<>(buildDiff(map1, map2));
        return Formatter.format(diffList, format);
    }

    public static String generate(String firstFilePath, String secondFilePath)
            throws Exception {
        Map<String, Object> map1 = Parser.parser(firstFilePath);
        Map<String, Object> map2 = Parser.parser(secondFilePath);
        List<Map<String, List<Object>>> diffList = new ArrayList<>(buildDiff(map1, map2));
        return Formatter.format(diffList, "stylish");
    }

    public static List<Map<String, List<Object>>> buildDiff(Map<String, Object> map1, Map<String, Object> map2)
            throws Exception {
        List<Map<String, List<Object>>> diffTree = new ArrayList<>();
        var allKeys = getSortedKeys(map1, map2);
        for (String key : allKeys) {
            if (map1.containsKey(key) && !map2.containsKey(key)) {
                diffTree.add(Map.of("removed", Arrays.asList(key, map1.get(key))));
            } else if (!map1.containsKey(key) && map2.containsKey(key)) {
                diffTree.add(Map.of("added", Arrays.asList(key, map2.get(key))));
            } else if (map1.get(key) == null && map2.get(key) == null) {
                diffTree.add(Map.of("unchanged", Arrays.asList(key, map1.get(key))));
            } else if ((map1.get(key) != null && map2.get(key) != null) && (map1.get(key).equals(map2.get(key)))) {
                diffTree.add(Map.of("unchanged", Arrays.asList(key, map1.get(key))));
            } else {
                diffTree.add(Map.of("updated", Arrays.asList(key, map2.get(key), map1.get(key))));
            }
        }
        return diffTree;
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
}
