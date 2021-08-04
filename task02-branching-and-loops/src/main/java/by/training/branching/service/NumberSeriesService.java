package by.training.branching.service;

/**
 * Describes the interface of a service that calculates number series with some condition.
 *
 * @author Nikita Romanov
 */
public interface NumberSeriesService {

    /**
     * Calculates sum of powers of two from 0 up to {@code lastPower}.
     *
     * @param lastPower the last lastPower for calculation inclusive.
     * @return sum of powers of two.
     * @throws ServiceException if {@code lastPower} is negative.
     */
    long calculateSumOfPowersOfTwo(int lastPower);

    /**
     * Calculates a series until the next element is not greater than {@code e} or equal to {@code e} inclusive.
     *
     * @param e the min value of the absolute value of the last member of the series.
     * @return sum of the series.
     * @throws ServiceException if {@code e} is negative or equal 0.
     */
    double calculateNumberSeries(double e);
}
