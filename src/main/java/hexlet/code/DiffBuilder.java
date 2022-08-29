package hexlet.code;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class DiffBuilder {
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

    private static Set<String> getSortedKeys(Map<String, Object> map1, Map<String, Object> map2) {
        final TreeSet<String> allKeys = new TreeSet<>(map1.keySet());
        allKeys.addAll(map2.keySet());
        return allKeys;
    }
}
