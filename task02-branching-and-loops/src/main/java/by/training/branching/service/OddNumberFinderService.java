package by.training.branching.service;

import java.util.List;

/**
 * Describes the interface of a service that finds odd numbers.
 *
 * @author Nikita Romanov
 */
public interface OddNumberFinderService {

    /**
     * Finds all odd numbers in the inclusive range.
     *
     * @param lowerBound lower inclusive bound of range.
     * @param upperBound upper inclusive bound of range.
     * @return list of odd numbers.
     * @throws ServiceException if the lower bound is greater than the upper bound.
     */
    List<Integer> findOddNumbersInRange(int lowerBound, int upperBound);
}
