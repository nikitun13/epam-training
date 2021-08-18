package by.training.arrays.dao;

import by.training.arrays.entity.Array;

import java.nio.file.Path;
import java.util.List;

/**
 * Describes the interface of a dao
 * that provides access to {@link Array}.
 *
 * @author Nikita Romanov
 * @see Array
 */
public interface ArrayDao {

    /**
     * Reads all {@code Arrays} from {@code path}.
     *
     * @param path {@code path} to {@code Array} source.
     * @return list of all {@code Arrays} that contain
     * in the {@code path} source.
     * @throws DaoException         if IO exception is occurred.
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    List<Array<String>> readAll(Path path);
}
