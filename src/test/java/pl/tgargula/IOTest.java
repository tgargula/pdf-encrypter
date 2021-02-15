package pl.tgargula;

import com.sun.jdi.connect.Connector;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IOTest {

    public static Stream<Arguments> files() {
        return Stream.of(
                Arguments.of("/app.fxml"),
                Arguments.of("/style.css"),
                Arguments.of("/popups/success.fxml"),
                Arguments.of("/popups/success.css"),
                Arguments.of("/popups/failure.fxml"),
                Arguments.of("/popups/failure.css"),
                Arguments.of("/popups/warning.fxml"),
                Arguments.of("/popups/warning.css")
        );
    }

    @ParameterizedTest
    @MethodSource("files")
    public void filesIOTest(String path) {
        assertNotNull(this.getClass().getResource(path));
    }
}
