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
import org.junit.jupiter.params.provider.NullSource;

import java.util.stream.Stream;

import static by.training.shapes.entity.VolumetricShape.Point;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class BallCalculatorServiceImplTest {

    private BallCalculatorServiceImpl service;

    private static final double DELTA = 0.00001;

    @BeforeAll
    void setUp() {
        service = new BallCalculatorServiceImpl();
    }

    @ParameterizedTest
    @MethodSource("positiveDataForCalculateSurfaceArea")
    @Tag("calculateSurfaceArea")
    void calculateSurfaceAreaIfReceivedValidData(Ball ball, double expected) throws ServiceException {
        double actual = service.calculateSurfaceArea(ball);
        assertEquals(expected, actual, DELTA,
                () -> "surface area of the ball: " + ball + " must be: " + expected);
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("invalidBallData")
    @Tag("calculateSurfaceArea")
    void throwServiceExceptionIfCalculateSurfaceAreaReceivedInvalidData(Ball ball) {
        assertThrows(ServiceException.class,
                () -> service.calculateSurfaceArea(ball),
                () -> "expected throwing " + ServiceException.class.getName());
    }

    @ParameterizedTest
    @MethodSource("positiveDataForCalculateVolume")
    @Tag("calculateVolume")
    void calculateVolumeIfReceivedValidData(Ball ball, double expected) throws ServiceException {
        double actual = service.calculateVolume(ball);
        assertEquals(expected, actual, DELTA,
                () -> "volume of the ball: " + ball + " must be: " + expected);
    }

    @ParameterizedTest
    @NullSource
    @MethodSource("invalidBallData")
    @Tag("calculateVolume")
    void throwServiceExceptionIfCalculateVolumeReceivedInvalidData(Ball ball) {
        assertThrows(ServiceException.class,
                () -> service.calculateVolume(ball),
                () -> "expected throwing " + ServiceException.class.getName());
    }

    @ParameterizedTest
    @MethodSource("positiveDataForCalculateRatioOfPartsDissectedByPlane")
    @Tag("calculateRatioOfPartsDissectedByPlane")
    void calculateRatioOfPartsDissectedByPlaneIfReceivedValidData(Ball ball, Plane plane, double expected) throws ServiceException {
        double actual = service.calculateRatioOfPartsDissectedByPlane(ball, plane);
        assertEquals(expected, actual, DELTA,
                () -> "ratio of the ball: " + ball + " dissected by plane: " + plane + " must be: " + expected);
    }

    @ParameterizedTest
    @MethodSource("negativeDataForCalculateRatioOfPartsDissectedByPlane")
    @Tag("calculateRatioOfPartsDissectedByPlane")
    void throwServiceExceptionIfCalculateRatioOfPartsDissectedByPlaneIfReceivedInvalidData(Ball ball, Plane plane) {
        assertThrows(ServiceException.class,
                () -> service.calculateRatioOfPartsDissectedByPlane(ball, plane),
                () -> "expected throwing " + ServiceException.class.getName());
    }

    @AfterAll
    void tearDown() {
        service = null;
    }

    public Stream<Arguments> positiveDataForCalculateSurfaceArea() {
        Point dummyPoint = new Point(0d, 0d, 0d);
        int id = 1;
        return Stream.of(
                Arguments.of(new Ball(id++, 0d, dummyPoint), 0d),
                Arguments.of(new Ball(id++, 1d, dummyPoint), 12.56637),
                Arguments.of(new Ball(id++, 3d, dummyPoint), 113.09734),
                Arguments.of(new Ball(id++, 0.5, dummyPoint), 3.14159),
                Arguments.of(new Ball(id++, 0.007, dummyPoint), 0.00062),
                Arguments.of(new Ball(id++, 0.1, dummyPoint), 0.12566),
                Arguments.of(new Ball(id++, 100d, dummyPoint), 125663.70614),
                Arguments.of(new Ball(id++, 10d, dummyPoint), 1256.63706),
                Arguments.of(new Ball(id++, 10.5, dummyPoint), 1385.44236),
                Arguments.of(new Ball(id++, 3.6, dummyPoint), 162.86016),
                Arguments.of(new Ball(id, 3.14, dummyPoint), 123.89939)
        );
    }

    public Stream<Arguments> invalidBallData() {
        Point dummyPoint = new Point(0d, 0d, 0d);
        int id = 1;
        return Stream.of(
                Arguments.of(new Ball(id++, 0d, null)),
                Arguments.of(new Ball(id++, 1d, null)),
                Arguments.of(new Ball(id++, 3d, null)),
                Arguments.of(new Ball(id++, 0.5, null)),
                Arguments.of(new Ball(id++, -0.007, dummyPoint)),
                Arguments.of(new Ball(id++, -0.1, dummyPoint)),
                Arguments.of(new Ball(id++, -100d, dummyPoint)),
                Arguments.of(new Ball(id++, -10d, dummyPoint)),
                Arguments.of(new Ball(id++, -10.5, null)),
                Arguments.of(new Ball(id++, -3.6, null)),
                Arguments.of(new Ball(id, -3.14, null))
        );
    }

    public Stream<Arguments> positiveDataForCalculateVolume() {
        Point dummyPoint = new Point(0d, 0d, 0d);
        int id = 1;
        return Stream.of(
                Arguments.of(new Ball(id++, 0d, dummyPoint), 0d),
                Arguments.of(new Ball(id++, 1d, dummyPoint), 4.18879),
                Arguments.of(new Ball(id++, 3d, dummyPoint), 113.09734),
                Arguments.of(new Ball(id++, 0.5, dummyPoint), 0.5236),
                Arguments.of(new Ball(id++, 0.07, dummyPoint), 0.00144),
                Arguments.of(new Ball(id++, 0.1, dummyPoint), 0.00419),
                Arguments.of(new Ball(id++, 100d, dummyPoint), 4_188_790.20479),
                Arguments.of(new Ball(id++, 10d, dummyPoint), 4_188.7902),
                Arguments.of(new Ball(id++, 10.5, dummyPoint), 4_849.04826),
                Arguments.of(new Ball(id++, 3.6, dummyPoint), 195.4322),
                Arguments.of(new Ball(id, 3.14, dummyPoint), 129.68136)
        );
    }

    public Stream<Arguments> positiveDataForCalculateRatioOfPartsDissectedByPlane() {
        int id = 1;
        return Stream.of(
                Arguments.of(new Ball(id++, 1d, new Point(0d, 0d, 0d)), Plane.XY_PLANE, 1d),
                Arguments.of(new Ball(id++, 1d, new Point(0d, 0d, 0d)), Plane.XZ_PLANE, 1d),
                Arguments.of(new Ball(id++, 1d, new Point(0d, 0d, 0d)), Plane.YZ_PLANE, 1d),
                Arguments.of(new Ball(id++, 0.5, new Point(0d, 0d, 0d)), Plane.XY_PLANE, 1d),
                Arguments.of(new Ball(id++, 0.5, new Point(0d, 0d, 0d)), Plane.XZ_PLANE, 1d),
                Arguments.of(new Ball(id++, 0.5, new Point(0d, 0d, 0d)), Plane.YZ_PLANE, 1d),
                Arguments.of(new Ball(id++, 1d, new Point(-343.2345, 3254.4354, 0.5)), Plane.XY_PLANE, 0.18518),
                Arguments.of(new Ball(id++, 1d, new Point(-343.2345, 0.5, -324.3)), Plane.XZ_PLANE, 0.18518),
                Arguments.of(new Ball(id++, 1d, new Point(0.5, -3254.4354, 4334.234)), Plane.YZ_PLANE, 0.18518),
                Arguments.of(new Ball(id++, 1d, new Point(-343.2345, 3254.4354, -0.5)), Plane.XY_PLANE, 0.18518),
                Arguments.of(new Ball(id++, 1d, new Point(-343.2345, -0.5, -324.3)), Plane.XZ_PLANE, 0.18518),
                Arguments.of(new Ball(id, 1d, new Point(-0.5, -3254.4354, 4334.234)), Plane.YZ_PLANE, 0.18518)
        );
    }

    public Stream<Arguments> negativeDataForCalculateRatioOfPartsDissectedByPlane() {
        int id = 1;
        return Stream.of(
                Arguments.of(new Ball(id++, 0d, new Point(0d, 0d, 0d)), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, 0d, new Point(0d, 0d, 0d)), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, 0d, new Point(0d, 0d, 0d)), Plane.YZ_PLANE),
                Arguments.of(new Ball(id++, 0d, new Point(1d, 2d, 3d)), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, 0d, new Point(4d, 5d, 6d)), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, 0d, new Point(7d, 8d, 9d)), Plane.YZ_PLANE),
                Arguments.of(new Ball(id++, 1d, new Point(1d, 1d, 1d)), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, 1d, new Point(1d, 1d, 1d)), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, 1d, new Point(1d, 1d, 1d)), Plane.YZ_PLANE),
                Arguments.of(new Ball(id++, 1d, new Point(10d, 13d, 12d)), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, 1d, new Point(-13d, -1.3d, -21d)), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, 1d, new Point(13d, -21d, -30d)), Plane.YZ_PLANE),
                Arguments.of(new Ball(id++, 0.5, new Point(0d, 0d, 1d)), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, 0.5, new Point(0d, 1d, 0d)), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, 0.5, new Point(1d, 0d, 0d)), Plane.YZ_PLANE),
                Arguments.of(new Ball(id++, -1d, new Point(0d, 0d, 0d)), Plane.XY_PLANE),
                Arguments.of(new Ball(id++, -5d, new Point(0d, 0d, 0d)), Plane.XZ_PLANE),
                Arguments.of(new Ball(id++, -8d, new Point(0d, 0d, 0d)), Plane.YZ_PLANE),
                Arguments.of(new Ball(id, 8d, new Point(0d, 0d, 0d)), null),
                Arguments.of(null, Plane.XY_PLANE),
                Arguments.of(null, Plane.XZ_PLANE),
                Arguments.of(null, Plane.YZ_PLANE),
                Arguments.of(null, null)
        );
    }
}
