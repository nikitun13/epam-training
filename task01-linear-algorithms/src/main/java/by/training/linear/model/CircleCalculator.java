package by.training.linear.model;

import by.training.linear.entity.Circle;
import by.training.linear.exception.InvalidCircleException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Objects;

public class CircleCalculator {

    private static final Logger logger = LogManager.getLogger(CircleCalculator.class);

    /*
     * 20. The circumference is known
     * Find the area of a circle bounded by this circumference.
     */
    public double calculateCircleArea(Circle circle) {
        logger.debug("received: {}", circle);
        Objects.requireNonNull(circle, "circle can't be null");
        double circumference = circle.getCircumference();
        if (circumference < 0d) {
            throw new InvalidCircleException("circumference can't be less than 0");
        }
        double result = Math.pow(circle.getCircumference(), 2) / (4 * Math.PI);
        logger.debug("result = {}", result);
        return result;
    }
}
