package by.training.shapes.dao.reader;

import by.training.shapes.dao.DaoException;

import java.nio.file.Path;
import java.util.List;

/**
 * Describes interface of a class that
 * provides access to data from the file.
 *
 * @author Nikita Romanov
 */
public interface Reader {

    /**
     * Reads all data from the file as lines.
     *
     * @param path path to file.
     * @return List of lines in the file.
     * @throws DaoException if IO exception occurred.
     */
    List<String> readAllLines(Path path) throws DaoException;
}
