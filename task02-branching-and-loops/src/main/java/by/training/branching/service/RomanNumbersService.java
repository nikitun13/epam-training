package by.training.branching.service;

import by.training.branching.entity.RomanInteger;

/**
 * Describes the interface of a service that converts roman numbers to arabic numbers.
 *
 * @author Nikita Romanov
 */
public interface RomanNumbersService {

    /**
     * Converts roman number to arabic number.
     *
     * @param romanNumber roman number.
     * @return arabic value of a roman number.
     * @throws ServiceException     if {@code romanNumber} is invalid.
     * @throws NullPointerException if {@code romanNumber} is {@code null}.
     */
    int fromRoman(RomanInteger romanNumber);
}
