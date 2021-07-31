package by.training.branching.service;

/**
 * Describes the interface of a service that provides an integer comparison.
 *
 * @author Nikita Romanov
 */
public interface IntegerComparatorService {

    /**
     * Returns the smallest of two integers.
     * If the integers have the same value, the result is that same value.
     *
     * @param a first number.
     * @param b second number.
     * @return the smallest of {@code a} and {@code b}.
     */
    int min(int a, int b);
}
