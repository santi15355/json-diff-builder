package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.DiffBuilder.buildDiff;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format) throws Exception {
        String stringFile1 = Files.readString(Path.of(firstFilePath));
        String stringFile2 = Files.readString(Path.of(secondFilePath));
        Map<String, Object> map1 = Parser.parser(stringFile1, firstFilePath);
        Map<String, Object> map2 = Parser.parser(stringFile2, secondFilePath);
        List<Map<String, List<Object>>> diffList = new ArrayList<>(buildDiff(map1, map2));
        return Formatter.format(diffList, format);
    }

    public static String generate(String firstFilePath, String secondFilePath)
            throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
    }
}
