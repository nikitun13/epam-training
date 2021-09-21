package by.training.shapes.dao;

import by.training.shapes.entity.Ball;

import java.nio.file.Path;
import java.util.List;

/**
 * Describes the interface of a dao that provides
 * access to {@link Ball} entities from the file.
 *
 * @author Nikita Romanov
 * @see Ball
 */
public interface BallDao {

    /**
     * Reads all {@link Ball} entities from the file.<br/>
     * Invalid data for creating {@link Ball} is ignored.
     *
     * @param path path to data.
     * @return list of {@link Ball} entities from the file.
     * @throws DaoException if IO exception is occurred.
     */
    List<Ball> getAllFromFile(Path path) throws DaoException;
}
