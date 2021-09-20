package by.training.shapes.service.impl;

import by.training.shapes.entity.Axis;
import by.training.shapes.entity.Ball;
import by.training.shapes.entity.Plane;
import by.training.shapes.entity.VolumetricShape;
import by.training.shapes.service.BallCalculatorService;
import by.training.shapes.service.ServiceException;
import by.training.shapes.service.validator.Validator;
import by.training.shapes.service.validator.impl.BallValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code BallCalculatorServiceImpl}
 * is a class that implements {@link BallCalculatorService}.
 *
 * @author Nikita Romanov
 */
public class BallCalculatorServiceImpl implements BallCalculatorService {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallCalculatorServiceImpl.class);
    /**
     * Coefficient for surface area formula.
     */
    private static final int AREA_COEFFICIENT = 4;
    /**
     * First coefficient for volume formula.
     */
    private static final int VOLUME_FIRST_COEFFICIENT = 4;
    /**
     * Second coefficient for volume formula.
     */
    private static final int VOLUME_SECOND_COEFFICIENT = 3;
    /**
     * Coefficient for part of ball volume formula.
     */
    private static final int PART_OF_BALL_VOLUME_COEFFICIENT = 3;
    /**
     * Second power for surface area formula.
     */
    private static final double SECOND_POWER = 2d;
    /**
     * Third power for volume formula.
     */
    private static final double THIRD_POWER = 3d;
    /**
     * Received ball message for logger.
     */
    private static final String RECEIVED_BALL_LOG_MESSAGE = "received ball: {}";
    /**
     * Invalid ball message for exception.
     */
    private static final String INVALID_BALL_EXCEPTION_MESSAGE = "invalid ball: ";

    /**
     * Validator for a {@code Ball}.
     */
    private final Validator<Ball> validator
            = BallValidatorImpl.getInstance();

    @Override
    public double calculateSurfaceArea(final Ball ball)
            throws ServiceException {
        log.debug(RECEIVED_BALL_LOG_MESSAGE, ball);
        if (!validator.isValid(ball)) {
            throw new ServiceException(INVALID_BALL_EXCEPTION_MESSAGE + ball);
        }
        double radius = ball.getRadius();
        double result = AREA_COEFFICIENT
                * Math.PI
                * Math.pow(radius, SECOND_POWER);
        log.debug("surface area: {}", result);
        return result;
    }

    @Override
    public double calculateVolume(final Ball ball) throws ServiceException {
        log.debug(RECEIVED_BALL_LOG_MESSAGE, ball);
        if (!validator.isValid(ball)) {
            throw new ServiceException(INVALID_BALL_EXCEPTION_MESSAGE + ball);
        }
        double radius = ball.getRadius();
        double result = VOLUME_FIRST_COEFFICIENT
                * Math.PI
                * Math.pow(radius, THIRD_POWER)
                / VOLUME_SECOND_COEFFICIENT;
        log.debug("volume: {}", result);
        return result;
    }

    @Override
    public double calculateRatioOfPartsDissectedByPlane(final Ball ball,
                                                        final Plane plane)
            throws ServiceException {
        log.debug("received ball: {}, plane: {}", ball, plane);
        if (plane == null || !validator.isValid(ball)) {
            throw new ServiceException(
                    String.format("invalid arguments: %s, %s", ball, plane)
            );
        }
        VolumetricShape.Point centerPoint = ball.getCenterPoint();
        Axis thirdAxis = getThirdAxis(plane);
        double thirdAxisValue = centerPoint.getAxisValue(thirdAxis);
        log.debug("third axis: {} and its value: {}",
                thirdAxis, thirdAxisValue
        );
        double radius = ball.getRadius();
        double absThirdAxisValue = Math.abs(thirdAxisValue);
        double length = radius - absThirdAxisValue;
        log.debug("smaller length: {}", length);
        if (length <= 0) {
            throw new ServiceException("plane doesn't dissect the ball");
        }
        double smallerPart = calculatePartOfBallVolume(radius, length);
        log.debug("smaller part volume: {}", smallerPart);
        double greaterPart = calculatePartOfBallVolume(radius,
                absThirdAxisValue + radius
        );
        log.debug("greater part volume: {}", greaterPart);
        double result = smallerPart / greaterPart;
        log.debug("ratio: {}", result);
        return result;
    }

    private double calculatePartOfBallVolume(final double radius,
                                             final double length) {
        log.debug("received radius: {}, length: {}", radius, length);
        double result = Math.PI
                * Math.pow(length, SECOND_POWER)
                * (radius - (length / PART_OF_BALL_VOLUME_COEFFICIENT));
        log.debug("volume of the ball part: {}", result);
        return result;
    }

    private Axis getThirdAxis(final Plane plane) {
        return switch (plane) {
            case XY_PLANE -> Axis.Z_AXIS;
            case XZ_PLANE -> Axis.Y_AXIS;
            case YZ_PLANE -> Axis.X_AXIS;
        };
    }
}
