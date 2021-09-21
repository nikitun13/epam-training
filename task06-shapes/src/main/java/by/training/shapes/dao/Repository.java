package by.training.shapes.dao;

import by.training.shapes.dao.specification.Specification;

import java.util.List;
import java.util.Optional;

/**
 * Describes interface of entity repository.<br/>
 * Provides access to data and ensures uniqueness of entities' {@code id}.
 *
 * @param <T> type of the entity.
 * @author Nikita Romanov
 */
public interface Repository<T> {

    /**
     * Adds new {@code entity} to the repository.<br/>
     * Assigns {@code id} to this entity.
     *
     * @param entity new {@code entity} to be added.
     * @return {@code true} if {@code entity} was added successfully,
     * {@code false} otherwise.
     */
    boolean add(T entity);

    /**
     * Adds new {@code entities} to the repository.<br/>
     * Assigns {@code id} to entities.
     *
     * @param entities new {@code entities} to be added.
     */
    void addAll(List<T> entities);

    /**
     * Finds {@code entity} from repository by its {@code id}.
     *
     * @param id {@code id} of the entity.
     * @return optional of entity with given {@code id}.
     */
    Optional<T> findById(int id);

    /**
     * Updates data of the {@code entity}.
     *
     * @param entity updated {@code entity}.
     * @return {@code true} if {@code entity} was updated successfully,
     * {@code false} otherwise.
     */
    boolean update(T entity);

    /**
     * Removes {@code entity} from the repository.
     *
     * @param entity {@code entity} to be deleted.
     * @return {@code true} if {@code entity} was deleted successfully,
     * {@code false} otherwise.
     */
    boolean remove(T entity);

    /**
     * Finds all entities from the repository.
     *
     * @return list of all entities from the repository.
     */
    List<T> getAll();

    /**
     * Finds {@code entity} from repository
     * by given {@code specification}.
     *
     * @param specification specification for filtering data.
     * @return list of entities matching the {@code specification}.
     * @see Specification
     */
    List<T> findBySpecification(Specification<T> specification);
}
