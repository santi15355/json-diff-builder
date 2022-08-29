package hexlet.code;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static hexlet.code.DiffBuilder.buildDiff;

public class Differ {
    public static String generate(String firstFilePath, String secondFilePath, String format)
            throws Exception {
        String stringFile1 = Files.readString(Path.of(firstFilePath));
        String stringFile2 = Files.readString(Path.of(secondFilePath));
        String extension1 = getFileExtension(firstFilePath);
        String extension2 = getFileExtension(secondFilePath);
        Map<String, Object> map1 = Parser.parser(stringFile1, extension1);
        Map<String, Object> map2 = Parser.parser(stringFile2, extension2);
        List<Map<String, List<Object>>> diffList = new ArrayList<>(buildDiff(map1, map2));
        return Formatter.format(diffList, format);
    }

    public static String generate(String firstFilePath, String secondFilePath)
            throws Exception {
        return generate(firstFilePath, secondFilePath, "stylish");
    }

    private static String getFileExtension(String path) throws IOException {
        String[] extension = path.split("\\.");
        return extension[1];
    }
}
