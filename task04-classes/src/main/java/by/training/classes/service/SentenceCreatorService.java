package by.training.classes.service;

import by.training.classes.entity.Sentence;

/**
 * Describes the interface of a service
 * that creates {@link Sentence}.
 *
 * @author Nikita Romanov
 * @see Sentence
 */
public interface SentenceCreatorService {

    /**
     * Creates {@link Sentence} using
     * string value of this {@code sentence}.
     *
     * @param value string value of the {@link Sentence}.
     * @return new {@code Sentence} from {@code value}.
     * @throws ServiceException if {@code value} is null.
     */
    Sentence createSentence(String value) throws ServiceException;
}
