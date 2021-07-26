package by.epam.linear.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExpressionCalculator {

    private static final Logger logger = LogManager.getLogger(ExpressionCalculator.class);

    /*
     * 40. The value 'x' is given.
     * Get the values -2x + 3x^2 - 4x^3 and 1 + 2x + 3x^2 + 4x^3.
     * Take care of saving operations.
     */

    //First expression: -2x + 3x^2 - 4x^3
    public long calculateFirstExpression(long x) {
        logger.debug("received: {}", x);
        long result = (long) (x * (-4 * Math.pow(x, 2) + 3 * x - 2)); //x * (-4x^2 + 3x - 2) = -2x + 3x^2 - 4x^3
        logger.debug("result = {}", result);
        return result;
    }

    //Second expression: 1 + 2x + 3x^2 + 4x^3
    public long calculateSecondExpression(long x) {
        logger.debug("received: {}", x);
        long result = (long) (4 * Math.pow(x, 3) + 3 * Math.pow(x, 2) + 2 * x + 1);
        logger.debug("result = {}", result);
        return result;
    }
}
