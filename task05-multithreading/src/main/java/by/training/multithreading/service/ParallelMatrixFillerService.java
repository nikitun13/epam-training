package by.training.multithreading.service;

import by.training.multithreading.entity.Matrix;

/**
 * Describes the interface of a service
 * that fills {@link Matrix} using multithreading.
 *
 * @author Nikita Romanov
 * @see Matrix
 */
public interface ParallelMatrixFillerService {

    /**
     * Fills {@code matrix} using multithreading.
     * It takes threads config from a file with path from
     * {@code pathToThreadsConfig}.
     *
     * @param matrix              matrix to be filled.
     * @param pathToThreadsConfig path to config for threads.
     * @throws ServiceException if {@code matrix} is invalid
     *                          or threads config are invalid
     *                          or {@code pathToThreadsConfig}
     *                          or {@code DaoException} occurred.
     */
    void fillMatrix(Matrix matrix, String pathToThreadsConfig)
            throws ServiceException;
}
