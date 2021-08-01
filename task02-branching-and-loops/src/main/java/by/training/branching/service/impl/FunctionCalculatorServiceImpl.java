package by.training.branching.service.impl;

import by.training.branching.service.FunctionCalculatorService;

/**
 * The class {@code FunctionCalculatorServiceImpl} is a class that implements {@link FunctionCalculatorService}.
 * <ul><li>If {@code x <= 13}, calculates function {@code F(x) = -x^3 + 9}.</li>
 * <li>If {@code x > 13}, calculates {@code F(x) = -(3 / (x + 1)}.</li></ul>
 *
 * @author Nikita Romanov
 */
public class FunctionCalculatorServiceImpl implements FunctionCalculatorService {

    @Override
    public double calculate(double x) {
        return 0; // stub
    }
}
