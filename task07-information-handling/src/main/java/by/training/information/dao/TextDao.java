package by.training.information.dao;

import by.training.information.entity.Text;

import java.nio.file.Path;

/**
 * Describes the interface of a dao that provides
 * access to {@link Text} entities from the file.
 *
 * @author Nikita Romanov
 * @see Text
 */
public interface TextDao {

    /**
     * Reads text from the file
     * and parses it as {@code text}.
     *
     * @param path path to file.
     * @return parsed {@code text}.
     * @throws DaoException if IO exception occurred.
     */
    Text read(Path path) throws DaoException;

    /**
     * Writes {@link Text} to the file.
     *
     * @param path path to file.
     * @param text text to be written to the file.
     * @throws DaoException if IO exception occurred.
     */
    void write(Path path, Text text) throws DaoException;
}
