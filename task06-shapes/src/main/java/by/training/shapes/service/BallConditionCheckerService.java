package by.training.shapes.service;

import by.training.shapes.entity.Ball;
import by.training.shapes.entity.Plane;

/**
 * Describes the interface of a service that provides
 * various condition checks for the {@link Ball}.
 *
 * @author Nikita Romanov
 * @see Ball
 */
public interface BallConditionCheckerService {

    /**
     * Checks if the {@link Ball} touches the {@link Plane}.
     *
     * @param ball  ball to check.
     * @param plane coordinate plane to check.
     * @return {@code true} if {@code ball} touches {@code plane},
     * {@code false} otherwise.
     * @throws ServiceException if {@code ball} is invalid
     *                          or {@code plane} is {@code null}.
     */
    boolean checkPlaneTouch(Ball ball, Plane plane) throws ServiceException;

    /**
     * Checks if the {@link Ball} is valid.
     *
     * @param ball ball to check.
     * @return {@code true} if the {@code ball} is valid,
     * {@code false} otherwise.
     */
    boolean isBall(Ball ball);

    /**
     * Checks if the {@link Ball} touches any {@link Plane}.
     *
     * @param ball ball to check.
     * @return {@code true} if the {@code ball} touches any {@link Plane},
     * {@code false} otherwise.
     * @throws ServiceException if {@code ball} is invalid.
     */
    default boolean checkTouchAnyPlane(Ball ball) throws ServiceException {
        return checkPlaneTouch(ball, Plane.XY_PLANE)
                || checkPlaneTouch(ball, Plane.XZ_PLANE)
                || checkPlaneTouch(ball, Plane.YZ_PLANE);
    }
}
