package by.training.branching.service;

/**
 * Describes the interface of a service that calculates functions.
 *
 * @author Nikita Romanov
 */
public interface FunctionCalculatorService {

    /**
     * Calculates the value of the function.
     *
     * @param x function argument.
     * @return function calculation result.
     * @throws ServiceException if the input value is not included in the set of values of the function.
     */
    double calculate(int x);
}
