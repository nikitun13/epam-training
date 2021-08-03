package by.training.branching.service.impl;

import by.training.branching.service.OddNumberFinderService;
import by.training.branching.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

/**
 * The class {@code OddNumberFinderServiceImpl} is a class that implements {@link OddNumberFinderService}.
 *
 * @author Nikita Romanov
 */
public class OddNumberFinderServiceImpl implements OddNumberFinderService {

    private static final Logger logger = LogManager.getLogger(OddNumberFinderServiceImpl.class);

    @Override
    public List<Integer> findOddNumbersInRange(int lowerBound, int upperBound) {
        logger.debug("received: lowerBound = {}, upperBound = {}", lowerBound, upperBound);
        if (lowerBound > upperBound) {
            throw new ServiceException("lower bound is greater than upper bound");
        }
        List<Integer> result = new ArrayList<>();
        if (lowerBound % 2 == 0) {
            lowerBound++;
        }
        while (lowerBound <= upperBound) {
            result.add(lowerBound);
            lowerBound += 2;
        }
        logger.debug("result: {}", result);
        return result;
    }
}
