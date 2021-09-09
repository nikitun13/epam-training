package by.training.multithreading.service;

import by.training.multithreading.entity.Matrix;

/**
 * Describes the interface of a service
 * that creates {@link Matrix}.
 *
 * @author Nikita Romanov
 * @see Matrix
 */
public interface MatrixCreatorService {

    /**
     * Creates {@code Matrix} with values
     * that are contained in the file.
     *
     * @param pathToFile path to file.
     * @return new {@code Matrix} with values
     * that are contained in the file.
     * @throws ServiceException if {@code Matrix} in the file is invalid
     *                          or IO exception is occurred
     *                          or {@code path} is {@code null}.
     */
    Matrix createFromFile(String pathToFile) throws ServiceException;
}
