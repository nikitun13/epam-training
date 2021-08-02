package by.training.branching.service;

import java.util.List;

/**
 * Describes the interface of a service that transforms real numbers depending on conditions.
 *
 * @author Nikita Romanov
 */
public interface RealNumberTransformerService {

    /**
     * Transforms two real numbers on the following condition.
     * It replaces the smaller of these two numbers with half of their sum,
     * and the larger with their doubled product.
     *
     * @param a first real number.
     * @param b second real number.
     * @return list of transformed {@code a} and {@code b}.
     * @throws ServiceException if {@code a} equals {@code b}.
     */
    List<Double> transformTwoDifferentRealNumbers(double a, double b);

    /**
     * Transforms three real numbers on the following condition.
     * <ul>
     *     <li>If {@code a} more than {@code b} and {@code b} more than {@code c}, it doubles these numbers.</li>
     *     <li>If not, it replaces them with absolute values.</li>
     * </ul>
     *
     * @param a first real number.
     * @param b second real number.
     * @param c third real number.
     * @return list of transformed {@code a}, {@code b} and {@code c}.
     */
    List<Double> transformThreeRealNumbers(double a, double b, double c);
}
