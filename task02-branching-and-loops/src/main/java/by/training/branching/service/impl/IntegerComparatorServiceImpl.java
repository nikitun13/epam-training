package by.training.branching.service.impl;

import by.training.branching.service.IntegerComparatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code IntegerComparatorServiceImpl} is a class that implements {@link IntegerComparatorService}.
 *
 * @author Nikita Romanov
 */
public class IntegerComparatorServiceImpl implements IntegerComparatorService {

    private static final Logger logger = LogManager.getLogger(IntegerComparatorServiceImpl.class);

    @Override
    public int min(int a, int b) {
        logger.debug("received: a = {}, b = {}", a, b);
        int result = a <= b ? a : b;
        logger.debug("result: {}", result);
        return result;
    }
}
