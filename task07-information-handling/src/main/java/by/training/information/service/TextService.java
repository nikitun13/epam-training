package by.training.information.service;

import by.training.information.entity.TextComposite;

/**
 * Describes the interface of a service that
 * provides access to {@code Text} entity.
 *
 * @author Nikita Romanov
 * @see TextComposite
 */
public interface TextService {

    /**
     * Reads text from the file and returns
     * it as {@link TextComposite}.
     *
     * @param pathToFile path to file.
     * @return TextComposite that represents {@code Text}.
     * @throws ServiceException if DAO exception occurred
     *                          or {@code pathToFile}
     *                          is {@code null} or invalid.
     */
    TextComposite readTextFromFile(String pathToFile) throws ServiceException;
}
