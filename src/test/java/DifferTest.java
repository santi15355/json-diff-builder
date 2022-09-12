import hexlet.code.Differ;
import hexlet.code.Formatter;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class DifferTest {

    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private final String pathJson1 = "./src/test/resources/example1.json";
    private final String pathJson2 = "./src/test/resources/example2.json";
    private final String pathYml1 = "./src/test/resources/example1.yml";
    private final String pathYml2 = "./src/test/resources/example2.yml";
    private final String emptyFile = "./src/test/resources/emptyFile.json";

    @BeforeAll
    public static void beforeAll() throws Exception {
        expectedStylish = Files.readString(Paths.get("./src/test/resources/expectedStylish.txt")).trim();
        expectedPlain = Files.readString(Paths.get("./src/test/resources/expectedPlain.txt")).trim();
        expectedJson = Files.readString(Paths.get("./src/test/resources/expectedJson.txt")).trim();
    }

    @Test
    public void differTestStylish() throws Exception {
        var actual = Differ.generate(pathJson1, pathJson2, "stylish");
        assertThat(actual).isEqualTo(expectedStylish);

        var actual2 = Differ.generate(pathJson1, pathJson2);
        assertThat(actual2).isEqualTo(expectedStylish);

        var actual3 = Differ.generate(pathYml1, pathJson2, "stylish");
        assertThat(actual3).isEqualTo(expectedStylish);

        var actual4 = Differ.generate(pathYml1, pathYml2, "stylish");
        assertThat(actual4).isEqualTo(expectedStylish);
    }

    @Test
    public void differTestPlain() throws Exception {
        var actual = Differ.generate(pathJson1, pathJson2, "plain");
        assertThat(actual).isEqualTo(expectedPlain);

        var actual2 = Differ.generate(pathYml1, pathYml2, "plain");
        assertThat(actual2).isEqualTo(expectedPlain);

        var actual3 = Differ.generate(pathJson1, pathYml2, "plain");
        assertThat(actual3).isEqualTo(expectedPlain);
    }

    @Test
    public void differTestJson() throws Exception {
        var actual = Differ.generate(pathJson1, pathJson2, "json");
        assertThat(actual).isEqualTo(expectedJson);

        var actual2 = Differ.generate(pathYml1, pathYml2, "json");
        assertThat(actual2).isEqualTo(expectedJson);

        var actual3 = Differ.generate(pathJson1, pathYml2, "json");
        assertThat(actual3).isEqualTo(expectedJson);
    }

    @Test
    public void differTestEmptyFile() {
        Throwable thrown = catchThrowable(() -> {
            Differ.generate(pathJson1, emptyFile);
        });
        assertThat(thrown).isInstanceOf(Exception.class);

    }

    @Test
    public void testFormatterStylish() {
        List<Map<String, List<Object>>> testDiff = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        list.add(0,"char");
        list.add(1,"char1");
        Map<String, List<Object>> map = new HashMap<>();
        map.put("none", list);
        testDiff.add(map);
        assertThrows(IllegalStateException.class, () ->
                Formatter.format(testDiff, "stylish"));
    }

    @Test
    public void testFormatterPlain() {
        List<Map<String, List<Object>>> testDiff = new ArrayList<>();
        List<Object> list = new ArrayList<>();
        list.add(0,"char");
        list.add(1,"char1");
        Map<String, List<Object>> map = new HashMap<>();
        map.put("none", list);
        testDiff.add(map);
        assertThrows(IllegalStateException.class, () ->
                Formatter.format(testDiff, "plain"));
    }


    @Test
    public void testExceptions() {
        // wrong format
        assertThrows(RuntimeException.class, () ->
                Differ.generate(pathJson1, pathJson2, "otherFormat"));

        // missing file
        assertThrows(Exception.class, () ->
                Differ.generate(pathJson1, "./src/test/resources/File.json"));

        //no extension
        assertThrows(NoSuchFileException.class, () ->
                Differ.generate("./src/test/resources/example1", "./src/test/resources/example2"));

        //wrong extension
        assertThrows(RuntimeException.class, () ->
                Differ.generate(pathJson1, "./src/test/resources/example2.tyt", "stylish"));
    }
}
