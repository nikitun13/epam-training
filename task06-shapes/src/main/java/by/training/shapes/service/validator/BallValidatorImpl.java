package by.training.shapes.service.validator;

import by.training.shapes.entity.Ball;

/**
 * The class {@code BallValidatorImpl}
 * is a class that implements {@link Validator}.<br/>
 * Validates {@link Ball} entities.
 *
 * @author Nikita Romanov
 * @see Validator
 * @see Ball
 */
public class BallValidatorImpl implements Validator<Ball> {

    /**
     * Singleton.
     */
    private static BallValidatorImpl instance;

    @Override
    public boolean isValid(final Ball shape) {
        return shape != null
                && shape.getCenterPoint() != null
                && shape.getRadius() >= 0d;
    }

    /**
     * Getter for singleton.
     *
     * @return singleton instance of {@link BallValidatorImpl} class.
     */
    public static BallValidatorImpl getInstance() {
        if (instance == null) {
            instance = new BallValidatorImpl();
        }
        return instance;
    }
}
