package by.training.shapes.service.validator.impl;

import by.training.shapes.entity.Ball;
import by.training.shapes.service.validator.Validator;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static by.training.shapes.entity.VolumetricShape.Point;
import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BallValidatorImplTest {

    private final Validator<Ball> validator = BallValidatorImpl.getInstance();

    @ParameterizedTest
    @MethodSource("dataForIsValid")
    @Tag("isValid")
    void testDifferentDataForIsValid(Ball ball, boolean expected) {
        boolean actual = validator.isValid(ball);
        assertEquals(expected, actual,
                () -> "must be %s for the ball: %s".formatted(expected, ball)
        );
    }

    public Stream<Arguments> dataForIsValid() {
        Point dummyPoint = new Point(0d, 0d, 0d);
        int id = 1;
        return Stream.of(
                Arguments.of(null, false),
                Arguments.of(new Ball(id++, -1d, dummyPoint), false),
                Arguments.of(new Ball(id++, -0.5, dummyPoint), false),
                Arguments.of(new Ball(id++, -0.00001, dummyPoint), false),
                Arguments.of(new Ball(id++, 1d, null), false),
                Arguments.of(new Ball(id++, 0.00000001, dummyPoint), true),
                Arguments.of(new Ball(id++, 1d, dummyPoint), true),
                Arguments.of(new Ball(id++, 0.5, dummyPoint), true),
                Arguments.of(new Ball(id++, 434543d, dummyPoint), true),
                Arguments.of(new Ball(id++, 434.543, dummyPoint), true),
                Arguments.of(new Ball(id++, 434.543, new Point(43, 4325.324, 3245.34)), true),
                Arguments.of(new Ball(id++, 434.543, new Point(-43, -4325.324, -3245.34)), true),
                Arguments.of(new Ball(id++, 434.543, new Point(-43, 4325.324, -3245.34)), true),
                Arguments.of(new Ball(id++, 434.543, new Point(43, 4325.324, -3245.34)), true),
                Arguments.of(new Ball(id, 434.543, new Point(-43, 4325.324, 3245.34)), true)
        );
    }
}