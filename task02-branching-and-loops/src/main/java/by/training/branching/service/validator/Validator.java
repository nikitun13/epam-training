package by.training.branching.service.validator;

/**
 * Describes the interface that checks if an object is valid.
 *
 * @param <T> object type for validation.
 * @author Nikita Romanov
 */
@FunctionalInterface
public interface Validator<T> {

    /**
     * Validates an object.
     *
     * @param o object for validation.
     * @return {@code true} if object is valid. Otherwise, {@code false}.
     */
    boolean isValid(T o);
}
