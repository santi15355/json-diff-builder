package hexlet.code;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.DiffBuilder.buildDiff;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format)
            throws Exception {
        String content1 = Files.readString(Path.of(firstFilePath));
        String content2 = Files.readString(Path.of(secondFilePath));
        Map<String, Object> data1 = Parser.parser(content1, getDataFormat(firstFilePath));
        Map<String, Object> data2 = Parser.parser(content2, getDataFormat(secondFilePath));
        List<Map<String, List<Object>>> diffList = new ArrayList<>(buildDiff(data1, data2));
        return Formatter.format(diffList, format);
    }

    public static String generate(String firstFilePath, String secondFilePath)
            throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    private static String getDataFormat(String filePath) {
        int index = filePath.lastIndexOf('.');
        return index > 0
                ? filePath.substring(index + 1)
                : "";
    }
}
