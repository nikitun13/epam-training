package by.training.shapes.service.impl;

import by.training.shapes.entity.Ball;
import by.training.shapes.entity.Plane;
import by.training.shapes.service.ServiceException;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static by.training.shapes.entity.VolumetricShape.Point;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BallConditionCheckerServiceImplTest {

    private BallConditionCheckerServiceImpl service;

    @BeforeAll
    void setUp() {
        service = new BallConditionCheckerServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("positiveDataForCheckPlaneTouch")
    @Tag("checkPlaneTouch")
    void checkPlaneTouchIfValidDataReceived(Ball ball, Plane plane, boolean expected) throws ServiceException {
        boolean actual = service.checkPlaneTouch(ball, plane);
        assertEquals(expected, actual,
                () -> "must be %s for ball: %s and plane: %s".formatted(expected, ball, plane)
        );
    }

    @ParameterizedTest
    @MethodSource("negativeDataForCheckPlaneTouch")
    @Tag("checkPlaneTouch")
    void mustThrowServiceExceptionIfCheckPlaneTouchReceivedInvalidData(Ball ball, Plane plane) {
        assertThrows(ServiceException.class,
                () -> service.checkPlaneTouch(ball, plane),
                () -> "expected throwing " + ServiceException.class.getName()
        );
    }

    @ParameterizedTest
    @MethodSource("dataForIsBall")
    @Tag("isBall")
    void testDifferentDataForIsBall(Ball ball, boolean expected) {
        boolean actual = service.isBall(ball);
        assertEquals(expected, actual,
                () -> "must be %s for the ball: %s".formatted(expected, ball)
        );
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> dataForIsBall() {
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

    public Stream<Arguments> positiveDataForCheckPlaneTouch() {
        int id = 1;
        return Stream.of(
                Arguments.of(new Ball(id++, 0d, new Point(0d, 0d, 0d)), Plane.XY_PLANE, true),
                Arguments.of(new Ball(id++, 0d, new Point(0d, 0d, 0d)), Plane.XZ_PLANE, true),
                Arguments.of(new Ball(id++, 0d, new Point(0d, 0d, 0d)), Plane.YZ_PLANE, true),
                Arguments.of(new Ball(id++, 0.00001, new Point(0d, 0d, 0d)), Plane.XY_PLANE, false),
                Arguments.of(new Ball(id++, 0.00001, new Point(0d, 0d, 0d)), Plane.XZ_PLANE, false),
                Arguments.of(new Ball(id++, 0.00001, new Point(0d, 0d, 0d)), Plane.YZ_PLANE, false),
                Arguments.of(new Ball(id++, 1d, new Point(0d, 0d, 1d)), Plane.XY_PLANE, true),
                Arguments.of(new Ball(id++, 1d, new Point(0d, 1d, 0d)), Plane.XZ_PLANE, true),
                Arguments.of(new Ball(id++, 1d, new Point(1d, 0d, 0d)), Plane.YZ_PLANE, true),
                Arguments.of(new Ball(id++, 1d, new Point(0d, 0d, -1d)), Plane.XY_PLANE, true),
                Arguments.of(new Ball(id++, 1d, new Point(0d, -1d, 0d)), Plane.XZ_PLANE, true),
                Arguments.of(new Ball(id++, 1d, new Point(-1d, 0d, 0d)), Plane.YZ_PLANE, true),
                Arguments.of(new Ball(id++, 1.00000001, new Point(0d, 0d, 1d)), Plane.XY_PLANE, false),
                Arguments.of(new Ball(id++, 1.00000001, new Point(0d, 1d, 0d)), Plane.XZ_PLANE, false),
                Arguments.of(new Ball(id++, 1.00000001, new Point(1d, 0d, 0d)), Plane.YZ_PLANE, false),
                Arguments.of(new Ball(id++, 1.00000001, new Point(0d, 0d, -1d)), Plane.XY_PLANE, false),
                Arguments.of(new Ball(id++, 1.00000001, new Point(0d, -1d, 0d)), Plane.XZ_PLANE, false),
                Arguments.of(new Ball(id++, 1.00000001, new Point(-1d, 0d, 0d)), Plane.YZ_PLANE, false), Arguments.of(new Ball(id++, 1.00000001, new Point(0d, 0d, 1d)), Plane.XY_PLANE, false),
                Arguments.of(new Ball(id++, 1d, new Point(0d, 1.00001, 0d)), Plane.XZ_PLANE, false),
                Arguments.of(new Ball(id++, 1d, new Point(-1.00001d, 0d, 0d)), Plane.YZ_PLANE, false),
                Arguments.of(new Ball(id++, 1d, new Point(0d, 0d, 1.000004d)), Plane.XY_PLANE, false),
                Arguments.of(new Ball(id++, 1d, new Point(0d, -1.0000003d, 0d)), Plane.XZ_PLANE, false),
                Arguments.of(new Ball(id++, 1d, new Point(1.000000003d, 0d, 0d)), Plane.YZ_PLANE, false),
                Arguments.of(new Ball(id++, 43.54, new Point(0d, 0d, 0d)), Plane.XY_PLANE, false),
                Arguments.of(new Ball(id++, 3546.432, new Point(0d, 0d, 0d)), Plane.XZ_PLANE, false),
                Arguments.of(new Ball(id++, 234.542, new Point(0d, 0d, 0d)), Plane.YZ_PLANE, false),
                Arguments.of(new Ball(id++, 443.5443, new Point(5434.32, 342.543, 32453.234)), Plane.XY_PLANE, false),
                Arguments.of(new Ball(id++, 3546.432, new Point(-345.76, -543.565, 2234.76)), Plane.XZ_PLANE, false),
                Arguments.of(new Ball(id++, 234.542, new Point(-543.5, -4.2354, -0.53565)), Plane.YZ_PLANE, false),
                Arguments.of(new Ball(id++, 32453.234, new Point(5434.32, 342.543, 32453.234)), Plane.XY_PLANE, true),
                Arguments.of(new Ball(id++, 543.565, new Point(-345.76, -543.565, 2234.76)), Plane.XZ_PLANE, true),
                Arguments.of(new Ball(id, 543.5, new Point(-543.5, -4.2354, -0.53565)), Plane.YZ_PLANE, true)
        );
    }

    public Stream<Arguments> negativeDataForCheckPlaneTouch() {
        Point dummyPoint = new Point(0d, 0d, 0d);
        int id = 1;
        return Stream.of(
                Arguments.of(null, Plane.XY_PLANE),
                Arguments.of(null, Plane.XZ_PLANE),
                Arguments.of(null, Plane.YZ_PLANE),
                Arguments.of(new Ball(id++, -1d, dummyPoint), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, -0.5, dummyPoint), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, -0.00001, dummyPoint), Plane.YZ_PLANE),
                Arguments.of(new Ball(id++, 1d, null), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, 0.5, null), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, 0.00001, null), Plane.YZ_PLANE),
                Arguments.of(new Ball(id, 1d, dummyPoint), null)
        );
    }
}
