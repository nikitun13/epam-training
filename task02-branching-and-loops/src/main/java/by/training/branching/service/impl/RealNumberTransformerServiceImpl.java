package by.training.branching.service.impl;

import by.training.branching.service.RealNumberTransformerService;
import by.training.branching.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

/**
 * The class {@code RealNumberTransformerServiceImpl} is a class that implements {@link RealNumberTransformerService}.
 *
 * @author Nikita Romanov
 */
public class RealNumberTransformerServiceImpl implements RealNumberTransformerService {

    private static final Logger logger = LogManager.getLogger(RealNumberTransformerServiceImpl.class);

    private static final int MULTIPLICATION_COEFFICIENT = 2;

    @Override
    public List<Double> transformTwoDifferentRealNumbers(double a, double b) {
        logger.debug("received a = {}, b = {}", a, b);
        if (a == b) {
            throw new ServiceException("'a' can't be equal to 'b'");
        }
        double lesser = (a + b) / 2;
        double greater = MULTIPLICATION_COEFFICIENT * a * b;
        List<Double> result;
        if (a > b) {
            result = List.of(greater, lesser);
        } else {
            result = List.of(lesser, greater);
        }
        logger.debug("result: {}", result);
        return result;
    }

    @Override
    public List<Double> transformThreeRealNumbers(double a, double b, double c) {
        logger.debug("received a = {}, b = {}, c = {}", a, b, c);
        List<Double> result;
        if (a > b && b > c) {
            result = List.of(
                    a * MULTIPLICATION_COEFFICIENT,
                    b * MULTIPLICATION_COEFFICIENT,
                    c * MULTIPLICATION_COEFFICIENT
            );
        } else {
            result = List.of(
                    Math.abs(a),
                    Math.abs(b),
                    Math.abs(c)
            );
        }
        logger.debug("result: {}", result);
        return result;
    }
}
