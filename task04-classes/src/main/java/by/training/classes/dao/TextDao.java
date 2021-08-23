package by.training.classes.dao;

import by.training.classes.entity.Text;

import java.nio.file.Path;

/**
 * Describes the interface of a dao
 * that provides access to {@link Text}.
 *
 * @author Nikita Romanov
 * @see Text
 */
public interface TextDao {

    /**
     * Saves string value of a {@link Text} to
     * the file that has the provided {@code path}.<br/>
     * This method overwrites the file.
     *
     * @param path path to the file.
     * @param text the string value of the {@link Text}
     *             to be written to the file.
     * @throws DaoException if IO exception occurred.
     */
    void save(Path path, String text) throws DaoException;

    /**
     * Reads {@link Text} from the file that
     * has the provided {@code path}.<br/>
     * Returns string value of the {@link Text} for
     * further parsing.
     *
     * @param path path to the file.
     * @return string value of the {@link Text}.
     * @throws DaoException if IO exception occurred.
     */
    String read(Path path) throws DaoException;

    /**
     * Deletes the file that has the provided {@code path}.
     *
     * @param path path to the file.
     * @throws DaoException if IO exception occurred.
     */
    void delete(Path path) throws DaoException;
}
