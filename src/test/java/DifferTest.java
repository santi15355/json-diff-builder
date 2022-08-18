import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.assertj.core.api.Assertions.assertThat;

public class DifferTest {
    public static String getExpected(String fileName) throws IOException {
        return Files.readString(Path.of("./src/test/resources/expected"));
    }

    @Test
    public void differNormalTest() throws Exception {
        var actual = Differ.generate("./src/test/resources/testFiles/file1.json",
                "./src/test/resources/testFiles/file2.json", "stylish");
        var expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose:true
                }""";
        assertThat(actual).isEqualTo(expected);
    }
}
