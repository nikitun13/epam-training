package by.training.arrays.service;

import by.training.arrays.entity.Matrix;

import java.nio.file.Path;
import java.util.List;

/**
 * Describes the interface of a service that creates {@link Matrix}.
 *
 * @author Nikita Romanov
 * @see Matrix
 */
public interface MatrixCreatorService {

    /**
     * Fills the {@code matrix} with randomized values
     * ranging from {@code minValue} to {@code maxValue} inclusively.
     *
     * @param matrix   matrix to be filled.
     * @param minValue minimum value of random numbers inclusive.
     * @param maxValue maximum value of random numbers inclusive.
     * @throws NullPointerException if the {@code matrix} is {@code null}.
     * @throws ServiceException     if {@code minValue}
     *                              is greater than {@code maxValue}.
     */
    void fillRandomized(Matrix matrix, int minValue, int maxValue);

    /**
     * Creates list of {@code Matrix} with values
     * that are contained in the file.<br>
     * Different {@code matrix} in the file must be separated
     * by an empty line.
     *
     * @param path path to file.
     * @return list of {@code Matrix} with values
     * that are contained in the file.
     * @throws ServiceException     if {@code Matrix} in the file is invalid
     *                              or IO exception is occurred.
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    List<Matrix> createFromFile(Path path);
}
