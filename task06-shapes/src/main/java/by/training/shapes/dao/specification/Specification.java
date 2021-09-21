package by.training.shapes.dao.specification;

import java.util.Comparator;
import java.util.Optional;

/**
 * Describes interface of a class
 * that provides filtering entities.
 *
 * @param <T> type of the {@code entity}.
 * @author Nikita Romanov
 */
public interface Specification<T> {

    /**
     * Checks if {@code entity} matches filter.
     *
     * @param entity {@code entity} to be checked.
     * @return {@code true} if {@code entity} matches filter,
     * {@code false} otherwise.
     */
    boolean isSpecified(T entity);

    /**
     * Optional {@link Comparator} if after
     * filtering entities need to be sorted.
     *
     * @return optional {@link Comparator}.
     */
    Optional<Comparator<T>> getOptionalComparator();
}
