package by.training.classes.service;

import by.training.classes.entity.Word;

/**
 * Describes the interface of a service
 * that creates {@link Word}.
 *
 * @author Nikita Romanov
 * @see Word
 */
public interface WordCreatorService {

    /**
     * Creates {@link Word} using
     * string value of this {@code word}.
     *
     * @param value string value of the {@link Word}.
     * @return new {@code Word} from {@code value}.
     * @throws ServiceException if {@code value} is null
     *                          or blank.
     */
    Word createWord(String value) throws ServiceException;
}
