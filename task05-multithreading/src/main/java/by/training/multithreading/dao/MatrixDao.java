package by.training.multithreading.dao;

import by.training.multithreading.entity.Matrix;

import java.nio.file.Path;

/**
 * Describes the interface of a dao
 * that provides access to {@link Matrix}.
 *
 * @author Nikita Romanov
 * @see Matrix
 */
public interface MatrixDao {

    /**
     * Reads {@code Matrix} from {@code path}.
     *
     * @param path {@code path} to file with {@code Matrix}.
     * @return {@code Matrix} from the file.
     * @throws DaoException if IO exception is occurred
     *                      or {@code path} is invalid.
     */
    Matrix read(Path path) throws DaoException;
}
