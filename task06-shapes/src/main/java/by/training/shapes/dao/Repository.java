package by.training.shapes.dao;

import java.util.List;
import java.util.Optional;

/**
 * Describes interface of entity repository.<br/>
 * Provides access to data.
 *
 * @param <T> type of the entity.
 * @author Nikita Romanov
 */
public interface Repository<T> {

    /**
     * Adds new {@code entity} to the repository.
     *
     * @param entity new {@code entity} to be added.
     * @return {@code true} if {@code entity} was added successfully,
     * {@code false} otherwise.
     */
    boolean add(T entity);

    /**
     * Finds {@code entity} from repository by its {@code id}.
     *
     * @param id {@code id} of the entity.
     * @return optional of entity with given {@code id}.
     */
    Optional<T> get(int id);

    /**
     * Updates data of the {@code entity}.
     *
     * @param entity updated {@code entity}.
     * @return {@code true} if {@code entity} was updated successfully,
     * {@code false} otherwise.
     */
    boolean update(T entity);

    /**
     * Deletes {@code entity} from the repository.
     *
     * @param entity {@code entity} to be deleted.
     * @return {@code true} if {@code entity} was deleted successfully,
     * {@code false} otherwise.
     */
    boolean delete(T entity);

    /**
     * Finds all entities from the repository.
     *
     * @return list of all entities from the repository.
     */
    List<T> getAll();
}
