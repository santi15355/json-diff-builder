import hexlet.code.Differ;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.catchThrowable;

public class DifferTest {

    private static String expectedStylish;
    private static String expectedPlain;
    private static String expectedJson;
    private final String pathJson1 = "./src/test/resources/example1.json";
    private final String pathJson2 = "./src/test/resources/example2.json";
    private final String pathYml1 = "./src/test/resources/example1.yml";
    private final String pathYml2 = "./src/test/resources/example2.yml";
    private final String emptyFile = "./src/test/resources/emptyFile.json";
    private Exception expected;

    @BeforeAll
    public static void beforeAll() throws IOException {
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
        assertThat(actual3).isEqualTo(expectedStylish);
    }
    @Test
    public void differTestPlain() throws Exception {
        var actual = Differ.generate(pathJson1, pathJson2, "plain");
        assertThat(actual).isEqualTo(expectedPlain);

        var actual2 = Differ.generate(pathYml1, pathYml2, "plain");
        assertThat(actual2).isEqualTo(expectedPlain);

        var actual3 = Differ.generate(pathJson1, pathYml2, "plain");
        assertThat(actual2).isEqualTo(expectedPlain);
    }

    @Test
    public void differTestJson() throws Exception {
        var actual = Differ.generate(pathJson1, pathJson2, "json");
        assertThat(actual).isEqualTo(expectedJson);

        var actual2 = Differ.generate(pathYml1, pathYml2, "json");
        assertThat(actual2).isEqualTo(expectedJson);

        var actual3 = Differ.generate(pathJson1, pathYml2, "json");
        assertThat(actual2).isEqualTo(expectedJson);
    }

    @Test
    public void differTestEmptyFile() {
        Throwable thrown = catchThrowable(() -> {
            Differ.generate(pathJson1, emptyFile);
        });
        assertThat(thrown).isInstanceOf(Exception.class);

    }
}
