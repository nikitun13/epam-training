package by.training.information.service;

import by.training.information.entity.ReversePolishNotation;

/**
 * Describes interface of a service that
 * calculates expression using {@link ReversePolishNotation}.
 *
 * @author Nikita Romanov
 * @see ReversePolishNotation
 */
public interface PolishNotationCalculatorService {

    /**
     * Calculates expression using {@link ReversePolishNotation}.
     *
     * @param polishNotation {@link ReversePolishNotation} for calculation.
     * @return calculation result.
     * @throws ServiceException if {@code polishNotation} is invalid.
     */
    int calculateExpression(ReversePolishNotation polishNotation)
            throws ServiceException;
}
