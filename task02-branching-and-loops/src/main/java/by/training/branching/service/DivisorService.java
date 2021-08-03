package by.training.branching.service;

import java.util.List;

/**
 * Describes the interface of a service that finds numbers that are divisible by a certain divisor.
 *
 * @author Nikita Romanov
 */
public interface DivisorService {

    /**
     * All elements of {@code numbers} are divided by a {@code divisor}.
     * Returns list of numbers that can be divided by a {@code divisor}.
     *
     * @param divisor divisor of numbers
     * @param numbers list of numbers
     * @return list of numbers that can be divided evenly by a {@code divisor}.
     * @throws ServiceException     if {@code divisor} equals {@code 0}.
     * @throws NullPointerException if {@code numbers} is null or element inside {@code numbers} is null.
     */
    List<Integer> findDivisibleNumbers(int divisor, List<Integer> numbers);

    /**
     * Finds all numbers not exceeding {@code bound} that are evenly divisible by all of their digits.
     *
     * @param bound inclusive bound of numbers.
     * @return list of numbers that are divisible by their digits.
     * @throws ServiceException if {@code bound} is less or equal {@code 0}.
     */
    List<Integer> findAllNumbersDivisibleByTheirDigits(int bound);
}
