package by.training.shapes.service;

import java.util.Optional;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;

/**
 * Describes interface of a service that
 * provides access to entities' registrar.
 *
 * @param <T> type of {@code entity}.
 * @param <R> type of entity's {@code registrar}.
 * @author Nikita Romanov
 */
public interface RegistrarService<T extends Publisher<T>, R extends Subscriber<T>> {

    /**
     * Returns entity's registrar.
     *
     * @param entity entity which {@code registrar} is need to be returned.
     * @return optional entity's {@code registrar}.
     */
    Optional<R> getRegistrar(T entity);
}
