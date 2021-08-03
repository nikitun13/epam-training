package by.training.branching.service.validator;

import by.training.branching.entity.RomanInteger;

/**
 * The class {@code RomanNumbersValidator} is a class that implements {@link Validator}.
 * It validates a {@link RomanInteger}.
 *
 * @author Nikita Romanov
 */
public class RomanNumbersValidator implements Validator<RomanInteger> {

    private static final String REGEX = "^M{0,3}(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$";

    @Override
    public boolean isValid(RomanInteger o) {
        if (o == null || o.getValue() == null || o.getValue().isBlank()) {
            return false;
        }
        return o.getValue().matches(REGEX);
    }
}
