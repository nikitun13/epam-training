package by.training.branching.service;

/**
 * Describes the interface of a service that calculates number series with some condition.
 *
 * @author Nikita Romanov
 */
public interface NumberSeriesService {

    /**
     * Calculates sum of powers of two from 0 up to {@code power}.
     *
     * @param power the last power for calculation inclusive.
     * @return sum of powers of two.
     * @throws ServiceException if {@code power} is less than 0.
     */
    long calculatePowersOfTwo(int power);

    /**
     * Calculates a series until the next element is not greater than {@code e} or equal to {@code e} inclusive.
     *
     * @param e the min value of the absolute value of the last member of the series.
     * @return sum of the series.
     * @throws ServiceException if {@code e} value is greater than any of the absolute values of the members of the series.
     */
    double calculateNumberSeries(double e);
}