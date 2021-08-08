package by.training.arrays.service;

import by.training.arrays.entity.Matrix;
import by.training.arrays.service.exception.ServiceException;

/**
 * Describes the interface of a service that creates {@code Matrix}.
 *
 * @author Nikita Romanov
 * @see Matrix
 */
public interface MatrixCreatorService {

    /**
     * Fills the {@code matrix} with randomized values
     * ranging from {@code minValue} to {@code maxValue}.
     *
     * @param matrix   matrix to be filled.
     * @param minValue minimum value of random numbers.
     * @param maxValue maximum value of random numbers.
     * @throws NullPointerException if the {@code matrix} is null.
     * @throws ServiceException     if {@code minValue} is greater than {@code maxValue}.
     */
    void fillRandomized(Matrix matrix, int minValue, int maxValue);
}
