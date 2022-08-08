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
    public void differNormalTest() throws IOException {
        var actual = Differ.generate("/home/vasiliy/file1.json", "/home/vasiliy/file2.json");
        var expected = """
                {
                  - follow: false
                    host: hexlet.io
                  - proxy: 123.234.53.22
                  - timeout: 50
                  + timeout: 20
                  + verbose: true
                }""";
        assertThat(actual).isEqualTo(expected);
    }
}
