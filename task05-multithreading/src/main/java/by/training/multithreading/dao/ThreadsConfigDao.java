package by.training.multithreading.dao;

import java.nio.file.Path;
import java.util.List;

/**
 * Describes the interface of a dao
 * that provides access to threads config.
 *
 * @author Nikita Romanov
 */
public interface ThreadsConfigDao {

    /**
     * Reads integers from {@code path}.
     *
     * @param path {@code path} to file with integers.
     * @return list of integers from the file for threads configuration.
     * @throws DaoException if IO exception is occurred
     *                      or {@code path} is invalid.
     */
    List<Integer> readConfigs(Path path) throws DaoException;
}
