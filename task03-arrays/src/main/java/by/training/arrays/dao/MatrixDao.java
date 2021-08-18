package by.training.arrays.dao;

import by.training.arrays.entity.Matrix;

import java.nio.file.Path;
import java.util.List;

/**
 * Describes the interface of a dao
 * that provides access to {@link Matrix}.
 *
 * @author Nikita Romanov
 * @see Matrix
 */
public interface MatrixDao {

    /**
     * Reads all {@code Matrix} from {@code path}.
     *
     * @param path {@code path} to {@code Matrix} source
     * @return list of all {@code Matrix} that contain
     * in the {@code path} source.
     * @throws DaoException         if IO exception is occurred.
     * @throws NullPointerException if {@code path} is {@code null}.
     */
    List<Matrix> readAll(Path path);
}
