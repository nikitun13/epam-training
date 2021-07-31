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
     */
    double calculate(int x);
}
