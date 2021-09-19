package by.training.shapes.service.impl;

import by.training.shapes.entity.Axis;
import by.training.shapes.entity.Ball;
import by.training.shapes.entity.Plane;
import by.training.shapes.entity.VolumetricShape;
import by.training.shapes.service.BallConditionCheckerService;
import by.training.shapes.service.ServiceException;
import by.training.shapes.service.validator.Validator;
import by.training.shapes.service.validator.impl.BallValidatorImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code BallConditionCheckerServiceImpl}
 * is a class that implements {@link BallConditionCheckerService}.
 *
 * @author Nikita Romanov
 * @see BallConditionCheckerService
 */
public class BallConditionCheckerServiceImpl
        implements BallConditionCheckerService {

    /**
     * Logger.
     */
    private static final Logger log
            = LogManager.getLogger(BallConditionCheckerServiceImpl.class);
    /**
     * Received ball message for logger.
     */
    private static final String RECEIVED_BALL_LOG_MESSAGE = "received ball: {}";
    /**
     * Validator for a {@code ball}.
     */
    private final Validator<Ball> validator
            = BallValidatorImpl.getInstance();

    @Override
    public boolean checkPlaneTouch(final Ball ball, final Plane plane)
            throws ServiceException {
        log.debug("received ball: {}, plane: {}", ball, plane);
        if (plane == null || !validator.isValid(ball)) {
            throw new ServiceException(
                    String.format("invalid arguments: %s, %s", ball, plane)
            );
        }
        Axis thirdAxis = getThirdAxis(plane);
        VolumetricShape.Point centerPoint = ball.getCenterPoint();
        double thirdAxisValue = centerPoint.getAxisValue(thirdAxis);
        log.debug("third axis: {} and its value: {}",
                thirdAxis, thirdAxisValue
        );
        double radius = ball.getRadius();
        double diff = radius - Math.abs(thirdAxisValue);
        log.debug("difference: {}", diff);
        return diff == 0d;
    }

    @Override
    public boolean isBall(final Ball ball) {
        log.debug(RECEIVED_BALL_LOG_MESSAGE, ball);
        boolean isValid = validator.isValid(ball);
        log.debug("is ball: {}", isValid);
        return isValid;
    }

    private Axis getThirdAxis(final Plane plane) {
        return switch (plane) {
            case XY_PLANE -> Axis.Z_AXIS;
            case XZ_PLANE -> Axis.Y_AXIS;
            case YZ_PLANE -> Axis.X_AXIS;
        };
    }
}
