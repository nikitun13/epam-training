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

    List<Array<String>> readAll(Path path);
}
