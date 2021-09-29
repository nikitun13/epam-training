package by.training.information.dao;

import by.training.information.entity.TextComposite;

import java.nio.file.Path;

/**
 * Describes the interface of a dao that provides
 * access to text entity from the file.
 *
 * @author Nikita Romanov
 * @see TextComposite
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
    TextComposite read(Path path) throws DaoException;
}
