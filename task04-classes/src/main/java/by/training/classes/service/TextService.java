package by.training.classes.service;

import by.training.classes.entity.Text;

import java.nio.file.Path;

/**
 * Describes the interface of a service
 * that provides saving, reading and deleting operations
 * with file using {@link Text}.
 *
 * @author Nikita Romanov
 * @see Text
 */
public interface TextService {

    /**
     * Reads {@link Text} from a file with the given path.
     *
     * @param path path to file with {@link Text}.
     * @return {@link Text} from the file.
     * @throws ServiceException if {@code path} is {@code null}
     *                          or read exception occurred
     *                          or invalid {@code Text} in the file.
     */
    Text readText(Path path) throws ServiceException;

    /**
     * Saves {@code text} to a file with the given {@code path}.<br/>
     * Creates new file or overwrites existing file.
     *
     * @param path path to file.
     * @param text text for saving to a file.
     * @throws ServiceException if {@code path} or {@code text} is {@code null}
     *                          or save exception occurred.
     */
    void saveText(Path path, Text text) throws ServiceException;

    /**
     * Deletes a file with the given path.
     *
     * @param path path to file with {@link Text}.
     * @throws ServiceException if {@code path} is {@code null}
     *                          or delete exception occurred.
     */
    void deleteText(Path path) throws ServiceException;
}
