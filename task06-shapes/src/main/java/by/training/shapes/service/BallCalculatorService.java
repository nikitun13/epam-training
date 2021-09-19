package by.training.shapes.service;

import by.training.shapes.entity.Ball;
import by.training.shapes.entity.Plane;

/**
 * Describes the interface of a service
 * that extends {@link VolumetricShapeCalculatorService}.
 *
 * @author Nikita Romanov
 * @see VolumetricShapeCalculatorService
 */
public interface BallCalculatorService
        extends VolumetricShapeCalculatorService<Ball> {

    /**
     * Returns the ratio of the volumes of the parts
     * dissected by the coordinate {@link Plane}.
     *
     * @param ball  ball to be dissected.
     * @param plane coordinate plane for dissection.
     * @return ratio of the parts.
     * @throws ServiceException if {@code ball} is invalid
     *                          or {@code plane} doesn't dissect {@code ball}
     *                          or {@code plane} is {@code null}.
     */
    double calculateRatioOfPartsDissectedByPlane(Ball ball, Plane plane)
            throws ServiceException;
}
