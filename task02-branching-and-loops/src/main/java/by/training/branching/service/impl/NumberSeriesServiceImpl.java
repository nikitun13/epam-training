package by.training.branching.service.impl;

import by.training.branching.service.NumberSeriesService;
import by.training.branching.service.ServiceException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The class {@code NumberSeriesServiceImpl} is a class that implements {@link NumberSeriesService}.
 *
 * @author Nikita Romanov
 */
public class NumberSeriesServiceImpl implements NumberSeriesService {

    private static final Logger logger = LogManager.getLogger(NumberSeriesServiceImpl.class);

    private static final int MEMBER_OF_SERIES_COEFFICIENT = 3;
    private static final int FIRST_MEMBER_OF_SERIES = -2;
    private static final int SECOND_MEMBER_OF_SERIES = 1;

    private static final int CALCULATION_MEMBER_FOR_SUM_OF_POWERS_OF_TWO = 1;

    @Override
    public long calculateSumOfPowersOfTwo(int lastPower) {
        logger.debug("received: {}", lastPower);
        if (lastPower < 0) {
            throw new ServiceException("Power can't be less than 0");
        }
        long result = (long) (Math.pow(2, (lastPower + CALCULATION_MEMBER_FOR_SUM_OF_POWERS_OF_TWO))
                - CALCULATION_MEMBER_FOR_SUM_OF_POWERS_OF_TWO);
        logger.debug("result: {}", result);
        return result;
    }

    @Override
    public double calculateNumberSeries(double e) {
        logger.debug("received e = {}", e);
        if (e <= 0) {
            throw new ServiceException("e can't be less than 0 or equal to 0");
        }
        double result = 0;
        double currentMember;
        int currentFactor = 1;
        while ((currentMember =
                1 / (double) ((MEMBER_OF_SERIES_COEFFICIENT * currentFactor + FIRST_MEMBER_OF_SERIES)
                        * (MEMBER_OF_SERIES_COEFFICIENT * currentFactor + SECOND_MEMBER_OF_SERIES))
        ) >= e) {
            result += currentMember;
            currentFactor++;
        }
        logger.debug("last member value: {}, last factor value: {}, result: {}",
                currentMember, currentFactor, result
        );
        return result;
    }
}
