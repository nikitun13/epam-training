package by.training.linear.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExpressionCalculator {

    private static final Logger logger = LogManager.getLogger(ExpressionCalculator.class);

    private static final int FREE_TERM_OF_FIRST_EXPRESSION = -2;
    private static final int FIRST_COEFFICIENT_OF_X_IN_FIRST_EXPRESSION = 3;
    private static final int SECOND_COEFFICIENT_OF_X_IN_FIRST_EXPRESSION = -4;

    private static final int FREE_TERM_OF_SECOND_EXPRESSION = 1;
    private static final int FIRST_COEFFICIENT_OF_X_IN_SECOND_EXPRESSION = 2;
    private static final int SECOND_COEFFICIENT_OF_X_IN_SECOND_EXPRESSION = 3;
    private static final int THIRD_COEFFICIENT_OF_X_IN_SECOND_EXPRESSION = 4;

    private static final int SECOND_POWER = 2;
    private static final int THIRD_POWER = 3;

    /*
     * 40. The value 'x' is given.
     * Get the values -2x + 3x^2 - 4x^3 and 1 + 2x + 3x^2 + 4x^3.
     * Take care of saving operations.
     */
    // First expression: -2x + 3x^2 - 4x^3 = x * (-4x^2 + 3x - 2)
    public long calculateFirstExpression(long x) {
        logger.debug("received: {}", x);
        long result = (long) (x * ((SECOND_COEFFICIENT_OF_X_IN_FIRST_EXPRESSION * Math.pow(x, SECOND_POWER))
                + (FIRST_COEFFICIENT_OF_X_IN_FIRST_EXPRESSION * x)
                + FREE_TERM_OF_FIRST_EXPRESSION)); // x * (-4x^2 + 3x - 2)
        logger.debug("result = {}", result);
        return result;
    }

    //Second expression: 1 + 2x + 3x^2 + 4x^3
    public long calculateSecondExpression(long x) {
        logger.debug("received: {}", x);
        long result = (long) ((THIRD_COEFFICIENT_OF_X_IN_SECOND_EXPRESSION * Math.pow(x, THIRD_POWER))
                + (SECOND_COEFFICIENT_OF_X_IN_SECOND_EXPRESSION * Math.pow(x, SECOND_POWER))
                + (FIRST_COEFFICIENT_OF_X_IN_SECOND_EXPRESSION * x)
                + FREE_TERM_OF_SECOND_EXPRESSION); // 1 + 2x + 3x^2 + 4x^3
        logger.debug("result = {}", result);
        return result;
    }
}
