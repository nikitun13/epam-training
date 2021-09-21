package by.training.shapes.dao.mapper;

import by.training.shapes.entity.Ball;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Queue;
import java.util.stream.Stream;

import static by.training.shapes.entity.VolumetricShape.Point;
import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BallMapperTest {

    private final BallMapper mapper = BallMapper.getInstance();

    @ParameterizedTest
    @MethodSource("validDataForIsValidLine")
    @Tag("isValidLine")
    void testValidDataForIsValidLine(String line) {
        boolean actual = mapper.isValidLine(line);
        assertTrue(actual, () -> "must be true for line: " + line);
    }

    @ParameterizedTest
    @MethodSource("invalidDataForIsValidLine")
    @Tag("isValidLine")
    void testInvalidDataForIsValidLine(String line) {
        boolean actual = mapper.isValidLine(line);
        assertFalse(actual, () -> "must be false for line: " + line);
    }

    @ParameterizedTest
    @MethodSource("validDataForMapFrom")
    @Tag("mapFrom")
    void testPositiveDataForMapFrom(String line, Ball expected) {
        Ball actual = mapper.mapFrom(line);
        assertEquals(expected, actual,
                () -> "must create ball: %s for line: %s".formatted(expected, line)
        );
    }

    public Stream<Arguments> validDataForIsValidLine() throws IOException {
        Path path = Path.of("src/test/resources/ball/01-ball-allValid.txt");
        return Files.readAllLines(path).stream()
                .map(Arguments::of);
    }

    public Stream<Arguments> invalidDataForIsValidLine() throws IOException {
        Path path = Path.of("src/test/resources/ball/01-ball-allInvalid.txt");
        return Files.readAllLines(path).stream()
                .map(Arguments::of);
    }

    public Stream<Arguments> validDataForMapFrom() throws IOException {
        Path path = Path.of("src/test/resources/ball/01-ball-allValid.txt");
        Queue<Ball> ballQueue = new ArrayDeque<>(
                List.of(
                        new Ball(1d, new Point(2d, 3d, 4d)),
                        new Ball(1.4, new Point(2.4, 3.5, 6.7)),
                        new Ball(0.4, new Point(1d, 0.2, 3d)),
                        new Ball(0d, new Point(0.1, 0.01, 0.001)),
                        new Ball(0d, new Point(0d, 0d, 0d)),
                        new Ball(1d, new Point(0d, 0d, 0d)),
                        new Ball(43.543, new Point(-32.432, -0.4325, -4.2354532)),
                        new Ball(4536, new Point(-234, 4325.3425, -3426.2345)),
                        new Ball(1.542, new Point(2.342, -3.3245, 4.3255)),
                        new Ball(1.542, new Point(-2.342, 3.3245, -4.3255)),
                        new Ball(100, new Point(1000000000, -432532524, -0.32454352345243))
                )
        );
        return Files.readAllLines(path).stream()
                .map(line -> Arguments.of(line, ballQueue.remove()));
    }
}
