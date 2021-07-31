package by.learning.branching.service.impl;

import by.learning.branching.service.IntegerComparatorService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
