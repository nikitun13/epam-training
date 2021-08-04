package by.training.branching.service;

/**
 * Describes the interface of a service that finds odd numbers.
 *
 * @author Nikita Romanov
 */
public interface OddNumberFinderService {

    /**
     * Finds the sum of all odd numbers in the inclusive range.
     *
     * @param lowerBound lower inclusive bound of range.
     * @param upperBound upper inclusive bound of range.
     * @return sum of odd numbers.
     * @throws ServiceException if the {@code lowerBound} is greater than the {@code upperBound}.
     */
    long findSumOfOddNumbersInRange(int lowerBound, int upperBound);
}
