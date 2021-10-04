package by.training.information.service;

import by.training.information.entity.ReversePolishNotation;

/**
 * Describes interface of a service that
 * creates {@link ReversePolishNotation}.
 *
 * @author Nikita Romanov
 * @see ReversePolishNotation
 */
public interface ReversePolishNotationCreatorService {

    /**
     * Creates {@link ReversePolishNotation}
     * from {@code expression}.
     *
     * @param expression expression to be transformed.
     * @return Reverse Polish notation from {@code expression}.
     * @throws ServiceException if {@code expression} is invalid.
     */
    ReversePolishNotation createNotation(String expression)
            throws ServiceException;
}
