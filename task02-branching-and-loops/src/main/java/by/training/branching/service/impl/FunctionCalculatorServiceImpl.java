package by.training.branching.service.impl;

import by.training.branching.service.FunctionCalculatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code FunctionCalculatorServiceImpl} is a class that implements {@link FunctionCalculatorService}.
 * <ul>
 *     <li>If {@code x <= 13}, calculates function {@code F(x) = -x^3 + 9}.</li>
 *     <li>If {@code x > 13}, calculates {@code F(x) = -(3 / (x + 1)}.</li>
 * </ul>
 *
 * @author Nikita Romanov
 */
public class FunctionCalculatorServiceImpl implements FunctionCalculatorService {

    private static final Logger logger = LogManager.getLogger(FunctionCalculatorServiceImpl.class);

    private static final int BOUND_OF_THE_FIRST_SCENARIO = 13;
    private static final int THIRD_POWER = 3;
    private static final int FREE_TERM_OF_THE_FUNCTION = 9;
    private static final int COEFFICIENT_OF_THE_FUNCTION = -3;
    private static final int FUNCTION_MEMBER = 1;

    @Override
    public double calculate(double x) {
        logger.debug("received x = {}", x);
        double result;
        if (x <= BOUND_OF_THE_FIRST_SCENARIO) {
            result = -Math.pow(x, THIRD_POWER) + FREE_TERM_OF_THE_FUNCTION;
        } else {
            result = COEFFICIENT_OF_THE_FUNCTION / (x + FUNCTION_MEMBER);
        }
        logger.debug("result: {}", result);
        return result;
    }
}
