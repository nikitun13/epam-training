package by.training.multithreading.service;

/**
 * Describes the interface of a service
 * that validates entities.
 *
 * @param <T> type of the entity.
 * @author Nikita Romanov
 */
public interface Validator<T> {

    /**
     * Validates entities.
     *
     * @param o entity to be validated.
     * @return {@code true} if entity is valid, {@code false} otherwise.
     */
    boolean isValid(T o);
}
